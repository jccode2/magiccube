/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-21 黄科林
 */
public class NotesMessage implements INotesMessage, Serializable{

	private static final long serialVersionUID = -1551439811832789742L;

	/** 抄送人地址 */
    private List<String> copyTo;
    
    /** 内容 */
    private String message;
    
    /** 发送邮箱的账号 */
    private String sendAccount;
    
    /** 发送人的邮箱 */
    private String sendMail;
    
    /** 发送邮箱的密码 */
    private String sendPassword;
    
    /** 发送服务器 */
    private String sendServer;
    
    /** 收件人地址 */
    private List<String> sendTo;
    
    /** 主题 */
    private String subject;
    
    /** 发送结果信息 */
    private String result;
    
    /**
     * @return copyTo
     * @see com.comtop.top.component.app.notes.INotesMessage#getCopyTo()
     */
    public List<String> getCopyTo() {
        return this.copyTo;
    }
    
    /**
     * @return message
     * @see com.comtop.top.component.app.notes.INotesMessage#getMessage()
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @return sendAccount
     * @see com.comtop.top.component.app.notes.INotesMessage#getSendAccount()
     */
    public String getSendAccount() {
        return sendAccount;
    }
    
    /**
     * @return sendMail
     * @see com.comtop.top.component.app.notes.INotesMessage#getSendMail()
     */
    public String getSendMail() {
        return sendMail;
    }
    
    /**
     * @return sendPassword
     * @see com.comtop.top.component.app.notes.INotesMessage#getSendPassword()
     */
    public String getSendPassword() {
        return sendPassword;
    }
    
    /**
     * @return sendServer
     * @see com.comtop.top.component.app.notes.INotesMessage#getSendServer()
     */
    public String getSendServer() {
        return sendServer;
    }
    
    /**
     * @return sendTo
     * @see com.comtop.top.component.app.notes.INotesMessage#getSendTo()
     */
    public List<String> getSendTo() {
        return this.sendTo;
    }
    
    /**
     * @return subject
     * @see com.comtop.top.component.app.notes.INotesMessage#getSubject()
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * 设置抄送人
     * 
     * @param copyTo 抄送人
     */
    public void setCopyTo(List<String> copyTo) {
        this.copyTo = copyTo;
    }
    
    /**
     * 设置邮件内容
     * 
     * @param message 邮件内容
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 设置发送方账号
     * 
     * @param sendAccount 发送方账号
     */
    public void setSendAccount(String sendAccount) {
        this.sendAccount = sendAccount;
    }
    
    /**
     * 设置发送方email地址
     * 
     * <pre>
     * 如 ：XX@163.com
     * </pre>
     * 
     * @param sendMail 发送方email地址
     */
    public void setSendMail(String sendMail) {
        this.sendMail = sendMail;
    }
    
    /**
     * 设置发送方Email的密码
     * 
     * @param sendPassword 发送方Email的密码
     */
    public void setSendPassword(String sendPassword) {
        this.sendPassword = sendPassword;
    }
    
    /**
     * 设置发送服务器地址
     * 
     * @param sendServer 发送服务器地址
     */
    public void setSendServer(String sendServer) {
        this.sendServer = sendServer;
    }
    
    /**
     * 设置邮件的主送人员
     * 
     * @param sendTo 主送
     */
    public void setSendTo(List<String> sendTo) {
        this.sendTo = sendTo;
    }
    
    /**
     * 设置邮件的主题
     * 
     * @param subject 邮件标题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * 发送的结果信息
     * 
     * @return 发送的结果
     */
    public String getResult() {
        return result;
    }
    
    /**
     * 设置邮件发送的结果信息
     * 
     * @param result 发送的结果
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        StringBuffer sbResult = new StringBuffer(256);
        sbResult.append(this.getClass().getName()).append("[");
        sbResult.append("sendServer:").append(this.sendServer).append(",");
        sbResult.append("sendMail:").append(this.sendMail).append(",");
        sbResult.append("sendAccount:").append(this.sendAccount).append(",");
        sbResult.append("sendTo:").append(this.sendTo).append(",");
        sbResult.append("copyTo:").append(this.copyTo).append(",");
        sbResult.append("subject:").append(this.subject).append(",");
        sbResult.append("message:").append(this.message).append(",");
        sbResult.append("result:").append(this.result);
        sbResult.append("]");
        return sbResult.toString();
    }

}
