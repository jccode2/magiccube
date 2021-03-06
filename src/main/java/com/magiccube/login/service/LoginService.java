/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.login.service;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.common.util.EnvUtils;
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
			resultVO = new ResultVO("账号或密码错误");
		}else{
			if (!password.equals(userVO.getPassword())) {
				resultVO = new ResultVO("账号或密码错误");
			}
		}
		EnvironmentInfoVO objEnvironmentInfoVO = new EnvironmentInfoVO();
		objEnvironmentInfoVO.setUser(userVO);
		WebContext objWebContext = WebContextFactory.get();
		objWebContext.getSession().setAttribute("environmentInfo", objEnvironmentInfoVO);
		if(resultVO.isSuccess()){
			objWebContext.getSession().setAttribute("current_user", userVO);
		}
		return resultVO;
	}
	
	/**
	 * 退出系统
	 */
	public void exit(){
		HttpSession session = EnvUtils.session();
		session.removeAttribute("environmentInfo");
		session.removeAttribute("current_user");
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
		//注册赠送10积分
		userVO.setScore(10);
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

	public boolean alreadyUsedName(UserVO userVO) {
		UserVO user = userDAO.queryUserByName(userVO.getUserName());
		if(user!=null){
			return true;
		}else{
			return false;
		}
	}

}
