/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳千方百味公司开发研制。
 ******************************************************************************/

package com.magiccube.login.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import com.magiccube.component.email.IMailWrapper;
import com.magiccube.component.email.MailResource;
import com.magiccube.component.email.MailSender;
import com.magiccube.core.base.model.ResultVO;
import com.magiccube.core.springmvc.ApplicationContextInitor;
import com.magiccube.core.util.tools.StringUtil;
import com.magiccube.login.service.LoginService;
import com.magiccube.login.util.PasswordModifyMailWrapper;
import com.magiccube.user.model.UserVO;

/**
 * 套餐action
 * 
 * @author 黄科林
 * @since jdk6.0
 * @version 2013-1-13 黄科林
 */
@RemoteProxy
public class LoginAction extends ApplicationObjectSupport {
    
    final static Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    
    static ApplicationContext context = null;
    
    static LoginService loginService = null;
    
    static {
        context = ApplicationContextInitor.getContext();
        loginService = (LoginService) context.getBean("LoginService");
    }
    
    /**
     * 登录
     * 
     * @param account
     */
    @RemoteMethod
    public ResultVO login(String account, String password) {
       return loginService.login(account, password);
    }
    
    /**
	 * 退出系统
	 */
    @RemoteMethod
	public void exit(){
		loginService.exit();
	}
    
    /**
     * 注册
     * 
     * @param userVO
     */
    @RemoteMethod
    public ResultVO regist(UserVO userVO) {
    	//用户名已经被注册
    	if(alreadyUsedName(userVO)){
    		 return new ResultVO("该用户名已经注册过");
    	}
        try {
        	userVO.setRegisterTime(new Date());
            loginService.regist(userVO);
        } catch (Exception e) {
            LOGGER.info("regist 出错：" + e);
            return new ResultVO(e.toString());
        }
        return new ResultVO();
    }
    
    private boolean alreadyUsedName(UserVO userVO) {
    	return loginService.alreadyUsedName(userVO);
	}

	/**
     * 向用户发送验证邮件
     * 
     * @param account 用户帐号、Email、手机号
     * @return
     */
    public ResultVO sendValidateEmail(String account) {
        ResultVO resultVO = null;
        try {
            UserVO user = loginService.getUser(account);
            if (user != null && StringUtil.isNotBlank(user.getEmail())) {
                MailSender mailSender = new MailSender();
                MailResource resources = new MailResource();
                IMailWrapper mailWrapper = new PasswordModifyMailWrapper(user.getEmail());
                mailSender.sendMail(resources, mailWrapper);
                resultVO = new ResultVO();
            }
        } catch (Exception e) {
            resultVO = new ResultVO(e.toString());
        }
        return resultVO;
    }
    
}
