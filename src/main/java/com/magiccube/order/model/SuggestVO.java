/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/

package com.magiccube.order.model;


import java.util.Date;

import com.magiccube.core.base.model.BaseVO;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-06-03 Xingling build
 */
public class SuggestVO extends BaseVO {
	
	private String userName;
	private String suggestContent;
	private Date suggestTime;
	
	
	public String getSuggestContent() {
		return suggestContent;
	}

	
	public void setSuggestContent(String suggestContent) {
		this.suggestContent = suggestContent;
	}

	
	public Date getSuggestTime() {
		return suggestTime;
	}

	
	public void setSuggestTime(Date suggestTime) {
		this.suggestTime = suggestTime;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
