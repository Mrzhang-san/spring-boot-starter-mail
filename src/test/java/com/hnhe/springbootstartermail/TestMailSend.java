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
    String title = "this is a test mail";
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

}
