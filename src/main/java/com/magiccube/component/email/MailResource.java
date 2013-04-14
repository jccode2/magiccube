/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import java.io.Serializable;

/**
 * 读取Email配置信息类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class MailResource implements Serializable {
	
	private static final long serialVersionUID = 900793432451602602L;

	/** 发送邮箱的账号 */
    private final String sendAccount = "2589430737";
    
    /** 发送人的邮箱 */
    private final String sendMail = "2589430737@qq.com";
    
    /** 发送邮箱的密码 */
    private final String sendPassword = "1000funs";
    
    /** 发送服务器 */
    private final String sendServer = "smtp.qq.com";
    
    /** 邮件服务器编码 */
    private final String mailServerEncoding = "GB2312";

	/**
	 * @return the sendAccount
	 */
	public String getSendAccount() {
		return sendAccount;
	}

	/**
	 * @return the sendMail
	 */
	public String getSendMail() {
		return sendMail;
	}

	/**
	 * @return the sendPassword
	 */
	public String getSendPassword() {
		return sendPassword;
	}

	/**
	 * @return the sendServer
	 */
	public String getSendServer() {
		return sendServer;
	}

	/**
	 * @return the mailServerEncoding
	 */
	public String getMailServerEncoding() {
		return mailServerEncoding;
	}
    
    
    
    /**
     * 初始化
     * 
     * @param configFileName 配置文件路径
     * @throws IOException 读写异常
     * @throws SAXException SAX异常
     * @throws ParserConfigurationException 解析配置异常
     */
    /*public EmailResource(String configFileName)  {
    	
        // 读配置文件，取得配置项
        this.sendServer = getValue(getNode("email", objDoc), "sendServer");
        this.sendMail = getValue(getNode("email", objDoc), "sendMail");
        this.sendAccount = getValue(getNode("email", objDoc), "sendAccount");
        this.sendPassword = getValue(getNode("email", objDoc), "sendPassword");
        this.mailServerEncoding = getValue(getNode("email", objDoc), "mailServerEncoding");
    }*/
    
    
    
}
