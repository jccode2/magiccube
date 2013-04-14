/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 通过SMTP方式向Domino Server 发送邮件实现类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class NotesMail {
	
	/** 日志管理类对象 */
	final static Logger LOGGER = LoggerFactory.getLogger(NotesMail.class);
    
    /**
     * 向DominoServer 发送邮件
     * 
     * @param notesMessage 发送邮件实例
     * @return <p>
     *         发送是否成功
     *         </p>
     *         <p>
     *         true 为发送成功
     *         </p>
     *         <p>
     *         false　发送失败
     *         </p>
     * @throws AddressException 地址错误异常
     * @throws MessagingException 消息错误异常
     */
    public boolean sendMessage(INotesMessage notesMessage) throws AddressException, MessagingException {
        
        // 消息为空时直接返回发送失败。
        if (notesMessage == null) {
            LOGGER.error("NotesMail error: 消息为空！");
            return false;
        }
        
        // 收件人不能为空
        if (notesMessage.getSendTo().size() < 1) {
            LOGGER.error("NotesMail error: 收件人为空！");
            return false;
        }
        
        Properties objProp = System.getProperties();
        objProp.put("mail.smtp.auth", "true");
        objProp.put("mail.smtp.host", notesMessage.getSendServer());
        NotesAuthenticator objNotesAuth =
            new NotesAuthenticator(notesMessage.getSendMail(), notesMessage.getSendPassword());
        Session objSession = Session.getInstance(objProp, objNotesAuth);
        MimeMessage objMimeMessage = new MimeMessage(objSession);
        
        // 设置发送人地址
        objMimeMessage.setFrom(new InternetAddress(notesMessage.getSendMail()));
        
        // 设置收件人地址
        List<String> objSendTo = notesMessage.getSendTo();
        InternetAddress[] objIAsSendTo = new InternetAddress[objSendTo.size()];
        for (int i = 0; i < objSendTo.size(); i++) {
            objIAsSendTo[i] = new InternetAddress(objSendTo.get(i).toString());
        }
        objMimeMessage.addRecipients(Message.RecipientType.TO, objIAsSendTo);
        
        // 设置抄送人地址
        List<String> objCopyTo = notesMessage.getCopyTo();
        if (objCopyTo != null && objCopyTo.size() > 0) {
            InternetAddress[] objIAsCopyTo = new InternetAddress[objCopyTo.size()];
            for (int i = 0; i < objCopyTo.size(); i++) {
                objIAsCopyTo[i] = new InternetAddress(objCopyTo.get(i).toString());
            }
            objMimeMessage.addRecipients(Message.RecipientType.CC, objIAsCopyTo);
        }
        
        // 设置主题（处理中文问题）
        objMimeMessage.setSubject(notesMessage.getSubject(), "GB2312");
        
        // 设置内容（添加charset=GB2312，处理中文问题）
        objMimeMessage.setContent(notesMessage.getMessage(), "text/html;charset=GB2312");
        objMimeMessage.saveChanges();
        Transport.send(objMimeMessage);
        return true;
    }
}
