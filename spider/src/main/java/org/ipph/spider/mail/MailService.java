package org.ipph.spider.mail;

import java.util.Map;

public interface MailService {
    public void sendSimpleMail(String to, String subject, String content);
    
    public void sendHtmlMail(String to, String subject, String content);
    
    public void sendHtmlTemplateMail(String to, String subject, String template,Map<String,Object> params);
}
