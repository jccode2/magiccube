/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件验证
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-14 黄科林
 */
public class NotesAuthenticator extends Authenticator {
	
	/** 用户邮箱 */
    private final String userMail;

    /** 邮箱密码 */
    private final String mailPassword;

    /**
     * 构造器
     * @param mail 邮箱地址
     * @param password 邮箱密码
     */
    public NotesAuthenticator(String mail, String password) {
        super();
        this.userMail = mail;
        this.mailPassword = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userMail, mailPassword);
    }
}
