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
    @Autowired
    private MailService mailService;

    /**
     * 测试简单文本发送
     */
    @Test
    public void send(){
        String receiver = "hnhe@ankki.com";
        String title = "this is a test mail";
        String content = "good goog study,and day day up!";
        Assert.assertTrue(mailService.send(receiver,title,content));

    }

}
