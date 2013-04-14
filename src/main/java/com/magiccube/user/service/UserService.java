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
}
