/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.core.util.tools.CharsetUtils;
import com.magiccube.core.util.tools.StringUtil;
import com.magiccube.packages.action.PackageAction;

/**
 * 邮件发送器
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class MailSender {

    final static Logger LOGGER = LoggerFactory.getLogger(PackageAction.class);
    
    /**
     * 发送邮件
     * 
     * @param reources 邮件资源
     * @param mailWrapper 邮件包装器
     * @return 发送成功与否 true 成功 false 失败
     */
    public boolean sendMail(MailResource resources, IMailWrapper mailWrapper) {
        if (mailWrapper == null || resources == null) {
            LOGGER.error("发送邮件时出错！（mailWrapper包装器对象为空或EmailResource为空。");
            return false;
        }
        NotesMessage objNotesMessage = new NotesMessage();
        objNotesMessage.setSendServer(resources.getSendServer());
        objNotesMessage.setSendMail(resources.getSendMail());
        objNotesMessage.setSendAccount(resources.getSendAccount());
        objNotesMessage.setSendPassword(resources.getSendPassword());
        
        // 给邮件设置发送邮箱
        objNotesMessage.setSendTo(mailWrapper.getSendTo());
        // 设置抄送地址
        objNotesMessage.setCopyTo(mailWrapper.getCopyTo());
        // 设置邮件主题
        objNotesMessage.setSubject(mailWrapper.getSubject());
        // 设置邮件内容
        String strEncoding = resources.getMailServerEncoding();
        if (StringUtil.isBlank(strEncoding)) {
            strEncoding = "GBK";
        }
        objNotesMessage.setMessage(CharsetUtils.changeCharset(mailWrapper.getContent(), strEncoding));
        NotesMail objNotesMail = new NotesMail();
        LOGGER.info("send " + objNotesMessage.getMessage() + " to " + objNotesMessage.getSendTo());
        try {
            objNotesMail.sendMessage(objNotesMessage);
        } catch (Throwable ex) {
            LOGGER.error("发送邮件时出错！（Mails:" + mailWrapper.getSendTo() + "）", ex);
            return false;
        }
        return true;
    }
}
