/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email.test;

import java.util.ArrayList;
import java.util.List;

import com.magiccube.component.email.IMailWrapper;

/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-23 黄科林
 */
public class MailWrapperDemo implements IMailWrapper{
	
	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getContent()
	 */
	@Override
	public String getContent() {
		String content = "this is email test!";
		return content;
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getCopyTo()
	 */
	@Override
	public List<String> getCopyTo() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getSendTo()
	 */
	@Override
	public List<String> getSendTo() {
		List<String> lstSendAddress = new ArrayList<String>();
		
		lstSendAddress.add("41987539@qq.com");
		return lstSendAddress;
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getSubject()
	 */
	@Override
	public String getSubject() {
		String strSubject = "【千方百味】邮件测试";
		return strSubject;
	}
}
