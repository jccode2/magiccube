/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.user.dao;

import com.magiccube.core.base.dao.BaseDAO;
import com.magiccube.user.model.UserVO;

/**
 * @author Xingling Chen
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class UserDAO extends BaseDAO {

	public void insertUser(UserVO userVO) {
		this.sqlSessionTemplate.insert("com.magiccube.user.insertUser", userVO);
	}
	
	public UserVO queryUserByName(String name){
		return (UserVO)this.sqlSessionTemplate.selectOne("com.magiccube.user.queryUserByName",name);
	}
	
	public UserVO queryUserByCodeOrEmailOrPhone(String account){
		return (UserVO)this.sqlSessionTemplate.selectOne("com.magiccube.user.queryUserByNameOrEmailOrPhone",account);
	}
	
}
