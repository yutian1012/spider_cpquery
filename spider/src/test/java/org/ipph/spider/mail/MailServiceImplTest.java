package org.ipph.spider.mail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {
    
    @Resource
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("yutian1012@126.com", "邮件发送测试", "测试！！！");
    }
    
    @Test
    public void testSendHtmlTemplateMail() {
    	mailService.sendHtmlTemplateMail("yutian1012@126.com", "测试", "mail/pageReportTemplate", null);
    }
}
