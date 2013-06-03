/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.user.service;

import com.magiccube.user.dao.UserDAO;
import com.magiccube.user.model.UserVO;

/**
 * @author Xingling Chen
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class UserService {

	private UserDAO userDAO;

	public void doInsert(UserVO userVO) {
		this.userDAO.insertUser(userVO);
	}
	
	public UserVO doQuery(String name){
		return userDAO.queryUserByName(name);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO testImpl) {
		this.userDAO = testImpl;
	}
	
	/**
	 * 获取电话号码的相应状态.
	 * @param phone
	 * @return -1:表示新号码; 0:表示号码处于正常状态; 1:表示黑名单
	 */
	public int getPhoneState(String phone) {
		return userDAO.getPhoneState(phone);
	}
	
	/**
	 * 批量获取多个电话号码的状态
	 * @param phones
	 * @return
	 */
	public int[] getPhoneStates(String[] phones) {
		int[] ret = new int[phones.length];
		for(int i = 0, len = phones.length; i < len; i++) {
			ret[i] = getPhoneState(phones[i]);
		}
		return ret;
	}
	
	/**
	 * 增加积分（设置参数为负即为减少积分）
	 * @param userName
	 * @param score
	 * @return
	 */
	public int addScore(String userName , int score){
		return userDAO.addScore(userName,score);
	}
	
}
