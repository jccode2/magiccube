/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.login.service;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.core.base.model.ResultVO;
import com.magiccube.user.dao.UserDAO;
import com.magiccube.user.model.UserVO;

/**
 * 登录Service
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class LoginService {
	
	final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	private UserDAO userDAO;
	
	/**
	 * 登录验证
	 * @param account 帐号，可以是帐号、email、手机号
	 * @param password 密码
	 * @return resultVO
	 */
	public ResultVO login(String account, String password){
		ResultVO resultVO = new ResultVO();
		UserVO userVO = userDAO.queryUserByCodeOrEmailOrPhone(account);
		if (userVO == null) {
			resultVO = new ResultVO("帐号不存在");
		}else{
			if (!password.equals(userVO.getPassword())) {
				resultVO = new ResultVO("密码不匹配");
			}
		}
		EnvironmentInfoVO objEnvironmentInfoVO = new EnvironmentInfoVO();
		objEnvironmentInfoVO.setUser(userVO);
		WebContext objWebContext = WebContextFactory.get();
		objWebContext.getSession().setAttribute("environmentInfo", objEnvironmentInfoVO);
		return resultVO;
	}
	
	/**
	 * 退出系统
	 */
	public void exit(){
		WebContext objWebContext = WebContextFactory.get();
		objWebContext.getSession().removeAttribute("environmentInfo");
	}
	
	/**
	 * 查询用户，根据帐号或者email或者phone
	 * @param account
	 * @return
	 */
	public UserVO getUser(String account){
		return userDAO.queryUserByCodeOrEmailOrPhone(account);
	}
	
	/**
	 * 注册
	 * @param userVO 用户信息
	 */
	public void regist(UserVO userVO){
		userDAO.insertUser(userVO);
	}
	
	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
