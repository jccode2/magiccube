/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/

package com.magiccube.user.model;

import java.util.Date;

import com.magiccube.core.base.model.BaseVO;
import com.magiccube.core.util.tools.DataGenerator;

/**
 * @author Xingling Chen
 * @since JDK1.6
 * @history 2011-05-11 Xingling build
 */
public class UserVO extends BaseVO{
	
	public static final int TYPE_CONSUMER = 0;
	public static final int TYPE_EMPLOYEE = 1;
	public static final int TYPE_MANAGER = 2;
	public static final int TYPE_ADMIN = 3;
	
	public static final int USER_STATE_BLACKLIST = 1;

	private int id;
	
	private String code;

	private String userName;
	
	private String password;

	private String phone;

	private String email;

	private Date registerTime;
	
	private int defaultRegionId;
	
	private String defaultAddress;
	
	/**
	 * User Type 
	 * 
	 * 0:consumer
	 * 1:employee
	 * 2:manager
	 * 3:admin
	 */
	private int userType;

	private int score;
	
	private double quota;
	
	/**
	 * 状态
	 * 0 - normal
	 * 1 - blacklist
	 */
	private int state;
	
	private boolean deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getQuota() {
		return quota;
	}

	public void setQuota(double quota) {
		this.quota = quota;
	}
	
	/**
	 * @return the defaultRegionId
	 */
	public int getDefaultRegionId() {
		return defaultRegionId;
	}

	/**
	 * @param defaultRegionId the defaultRegionId to set
	 */
	public void setDefaultRegionId(int defaultRegionId) {
		this.defaultRegionId = defaultRegionId;
	}

	/**
	 * @return the defaultAddress
	 */
	public String getDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress the defaultAddress to set
	 */
	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	/**
	 * @return the userType
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static void main(String[] args) {
		UserVO user = (UserVO)DataGenerator.get(UserVO.class);
		System.out.println(user.toString());
	}

}
