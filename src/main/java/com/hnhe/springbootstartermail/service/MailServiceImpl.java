package com.hnhe.springbootstartermail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public boolean send(String to, String subject, String content) {
        System.out.println("## ready to send mail...");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送来源
        simpleMailMessage.setFrom(mailProperties.getUsername());
        //邮件发送目标
        simpleMailMessage.setTo(to);
        //邮件发送主题
        simpleMailMessage.setSubject(subject);
        //邮件发送内容
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            System.out.println("邮件发送异常");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendWithHtml(String to, String subject, String html) {
        System.out.println("准备发送邮件");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送来源 发件人
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            //邮件发送目标
            mimeMessageHelper.setTo(to);
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //设置内容 并设置内容为html格式 true
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("邮件发送失败");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendWithPicHtml(String to, String subject, String html, String[] cids, String[] filePaths) {
        System.out.println("开始发送邮件");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
                try{
                    mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    mimeMessageHelper.setFrom(mailProperties.getUsername());
                    mimeMessageHelper.setTo(to);
                    mimeMessageHelper.setSubject(subject);
                    mimeMessageHelper.setText(html,true);

                    //设置html中关联图片
                    for (int i = 0; i < cids.length; i++) {
                        FileSystemResource file = new FileSystemResource(filePaths[i]);
                        // addInline cid 需要html中的cid （content id）对应 才能设置图片成功
                        mimeMessageHelper.addInline(cids[i],file);
                    }
                    javaMailSender.send(mimeMessage);
                    System.out.println("邮件发送成功！");

                }catch ( Exception e) {
                    System.out.println("邮件发送异常");
                    e.printStackTrace();
                    return false;
                }

        return true;
    }

    @Override
    public boolean sendWithWithEnclosure(String to, String subject, String content, String[] filePaths) {
        System.out.println("准备开始发送邮件...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            for (int i = 0; i < filePaths.length; i++) {
                FileSystemResource file = new FileSystemResource(filePaths[i]);
                String attachFileName = "附件" +(i+1);
                mimeMessageHelper.addAttachment(attachFileName,file);
            }
            javaMailSender.send(mimeMessage);
            System.out.println("发送邮件成功");
        } catch (Exception e) {
            System.out.println("发送邮件异常");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
