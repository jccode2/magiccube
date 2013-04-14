/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.login.util;

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
public class PasswordModifyMailWrapper implements IMailWrapper{
	
	private String content;
	
	private List<String> copyTO = new ArrayList<String>(10);
	
	private List<String> sendTO = new ArrayList<String>(10);
	
	private String subject;
	
	public PasswordModifyMailWrapper(String userEmail){
		this.sendTO.add(userEmail);
	}
	
	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getContent()
	 */
	@Override
	public String getContent() {
		content = "1000funs.com用户密码重置，请点击下面的链接地址进行密码修改：<br><a href='http://www.1000funs.com'>http://www.1000funs.com</a>";
		return content;
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getCopyTo()
	 */
	@Override
	public List<String> getCopyTo() {
		return copyTO;
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getSendTo()
	 */
	@Override
	public List<String> getSendTo() {
		return sendTO;
	}
	
	/**
	 * @param sendTO the sendTO to set
	 */
	public void setSendTO(List<String> sendTO) {
		this.sendTO = sendTO;
	}
	
	public void addSendTO(){
		
	}

	/* (non-Javadoc)
	 * @see com.magiccube.component.email.IMailWrapper#getSubject()
	 */
	@Override
	public String getSubject() {
		subject = "【千方百味】密码修改";
		return subject;
	}
}
