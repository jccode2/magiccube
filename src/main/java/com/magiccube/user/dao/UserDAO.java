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
	
	/**
	 * 获取电话号码的相应状态.
	 * @param phone
	 * @return -1:表示新号码; 0:表示号码处于正常状态; 1:表示黑名单
	 */
	public int getPhoneState(String phone) {
		int ret = -1;
		try {
			ret = (Integer)this.sqlSessionTemplate.selectOne("com.magiccube.user.getPhoneState", phone);
		} catch (NullPointerException e) { //号码不存在,返回-1.
		}
		return ret;
	}

	/**
	 * 为用户增加积分
	 * @param userName
	 * @param getScore
	 * @return
	 */
	public int addScore(String userName, int getScore) {
		UserVO userVO = new UserVO();
		userVO.setUserName(userName);
		userVO.setScore(getScore);
		try{
			this.sqlSessionTemplate.update("com.magiccube.user.addScore", userVO);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
}
