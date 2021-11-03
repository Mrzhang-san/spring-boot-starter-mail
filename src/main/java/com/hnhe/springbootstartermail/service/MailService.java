package com.hnhe.springbootstartermail.service;


public interface MailService {

    /**
     * 发送简单文本文件
     * @param to
     * @param subject
     * @param content
     * @return
     */
    boolean send(String to, String subject,String content);

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param html
     * @return
     */
    boolean sendWithHtml(String to,String subject,String html);

    /**
     * 发送带有附件的邮件
     * @param to
     * @param subject
     * @param html
     * @param cids
     * @param filePaths
     * @return
     */
    boolean sendWithPicHtml(String to,String subject,String html,String [] cids,String [] filePaths);

    /**
     * 发送带有附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @return
     */
    boolean sendWithWithEnclosure(String to,String subject,String content,String[] filePaths);


}
