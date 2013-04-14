/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.address.model;

import com.magiccube.core.base.model.BaseVO;

/**
 * AddressVO与RegionVO的区别是，Address包含了详细地址，而Region只是区域，如：香丽大厦、中投大厦。
 * 
 * @author Xingling
 * @since JDK1.6
 * @history 2013-01-13 Xingling build
 */
public class AddressVO extends BaseVO{
	private int id;
	private String shortName;
	private String fullName;
	private String image;
	private String phone;
	private String userIp;
	private String inputTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

}
