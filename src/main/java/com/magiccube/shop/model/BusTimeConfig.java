/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.shop.model;

import org.directwebremoting.annotations.DataTransferObject;

import com.magiccube.core.base.model.BaseVO;

/**
 * 
 * 营业时间配置类
 *
 * @since 2013-9-3
 * @author ksfifa
 */
public class BusTimeConfig extends BaseVO{

	/**开门时间**/
	private String openTime;
	
	/**打烊时间**/
	private String closingTime;
	
	/**星期天是否营业*/
	private String sundayClose;

	/**
	 * @return the openTime
	 */
	public String getOpenTime() {
		return openTime;
	}

	/**
	 * @param openTime the openTime to set
	 */
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	/**
	 * @return the closingTime
	 */
	public String getClosingTime() {
		return closingTime;
	}

	/**
	 * @param closingTime the closingTime to set
	 */
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	/**
	 * @return the sundayClose
	 */
	public String getSundayClose() {
		return sundayClose;
	}

	/**
	 * @param sundayClose the sundayClose to set
	 */
	public void setSundayClose(String sundayClose) {
		this.sundayClose = sundayClose;
	}

	
	
	
	
}
