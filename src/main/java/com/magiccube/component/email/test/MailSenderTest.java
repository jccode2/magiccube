/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email.test;

import com.magiccube.component.email.IMailWrapper;
import com.magiccube.component.email.MailResource;
import com.magiccube.component.email.MailSender;

/**
 * 邮件测试类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-21 黄科林
 */
public class MailSenderTest {
	
	public static void main(String[] args) {
		MailSender mailSender = new MailSender();
		MailResource resources = new MailResource();
		IMailWrapper mailWrapper = new MailWrapperDemo();
		mailSender.sendMail(resources, mailWrapper);
	}
}