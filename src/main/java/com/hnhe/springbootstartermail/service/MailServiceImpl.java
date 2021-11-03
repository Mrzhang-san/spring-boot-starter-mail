package com.hnhe.springbootstartermail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
        return false;
    }

    @Override
    public boolean sendWithPicHtml(String to, String subject, String html, String[] cids, String[] filePaths) {
        return false;
    }

    @Override
    public boolean sendWithWithEnclosure(String to, String subject, String content, String[] filePaths) {
        return false;
    }
}
