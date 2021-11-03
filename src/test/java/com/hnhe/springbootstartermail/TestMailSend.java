package com.hnhe.springbootstartermail;


import com.hnhe.springbootstartermail.service.MailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailSend {
    String receiver = "hnhe@ankki.com";
    String title = "this is a mail";
    @Autowired
    private MailService mailService;

    /**
     * 测试简单文本发送
     */
    @Test
    public void send(){
        String content = "good goog study,and day day up!";
        Assert.assertTrue(mailService.send(receiver,title,content));

    }

    @Test
    public void sendWithHtml(){
        String htmlContent= "<html><body><h1>好好学习，天天向上</h1><body></html>";
        Assert.assertTrue(mailService.sendWithHtml(receiver,title,htmlContent));
    }

    /**
     * 测试发送html带图片的邮件
     */
    @Test
    public void sendWithPicHtml(){
        String htmlContent = "<html><body>" +
                "<h1>欢迎关注微信公众号: 呵呵英语</h1>" +
                "<p><h2 style='color: red;'>图片1</h2><img style='width: 280px; height: 280px;' src='cid:test'></p>" +
                "<p><h2 style='color: red;'>图片2</h2><img style='width: 280px; height: 280px;' src='cid:test2'></p>" +
                "</body></html>";

        // cid 要与上面 html 中的 cid 对应，否则设置图片不成功
        String[] cids = new String[]{
                "test",
                "test2",
        };
        //文件路径
        String[] filePaths = new  String[] {
                "C:\\Users\\user\\Desktop\\ddd\\原始.png",
                "C:\\Users\\user\\Desktop\\ddd\\翻译1.png"
        };
        /**
         * 填写相关测试参数，包括目标邮箱地址，标题，html 内容，html 中包含了两张图片，
         * 并且 src 中的内容是 cid:{flag}的格式，前缀 cid:是固定的，
         * 您需要改变是后面的标志位，通过 addInline(cid, file) 来将 cid 和具体的图片文件对应起来。
         */
       // mailService.sendWithPicHtml(receiver,title,htmlContent,cids,filePaths);
        Assert.assertTrue(mailService.sendWithPicHtml(receiver,title,htmlContent,cids,filePaths));
    }

    /**
     * 测试发送带附件的邮件
     */
    @Test
    public void sendWithWithEnclosure(){
        String content = "带有附件的邮件发送测试";
        String[] filePaths = new String[] {
                "C:\\Users\\user\\Desktop\\ddd\\原始.png",
                "C:\\Users\\user\\Desktop\\ddd\\翻译1.png"
        };
        Assert.assertTrue(mailService.sendWithWithEnclosure(receiver,title,content,filePaths));
    }
}
