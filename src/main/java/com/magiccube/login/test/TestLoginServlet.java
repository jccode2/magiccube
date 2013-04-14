package com.magiccube.login.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magiccube.core.base.model.ResultVO;
import com.magiccube.login.action.LoginAction;
import com.magiccube.user.model.UserVO;

public class TestLoginServlet extends HttpServlet {

	static LoginAction action = new LoginAction();
	
	static void login(){
		System.out.println("==========用户帐号登录测试===================");
		// 用户帐号登录
		ResultVO result1 = action.login("colin", "hello");
		ResultVO result2 = action.login("colin1", "hello");
		ResultVO result3 = action.login("colin", "hello1");
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		if (result1.isSuccess() && !result2.isSuccess() && !result3.isSuccess()) {
			System.out.println("【用户帐号登录测试通过】");
		}else{
			System.out.println("!!!!用户帐号登录测试未通过!!!!");
		}
		// email帐号登录 
		System.out.println("==========email帐号登录测试===================");
		result1 = action.login("41987539@qq.com", "hello");
		result2 = action.login("41987539@qq.com1", "hello");
		result3 = action.login("41987539@qq.com", "hello1");
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		if (result1.isSuccess() && !result2.isSuccess() && !result3.isSuccess()) {
			System.out.println("【email帐号登录测试通过】");
		}else{
			System.out.println("!!!!email帐号登录测试未通过!!!!");
		}
		
		// 手机帐号登录 
		System.out.println("==========手机帐号登录===================");
		result1 = action.login("13488888888", "hello");
		result2 = action.login("134888888881", "hello");
		result3 = action.login("13488888888", "hello1");
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		if (result1.isSuccess() && !result2.isSuccess() && !result3.isSuccess()) {
			System.out.println("【手机帐号登录测试通过】");
		}else{
			System.out.println("!!!!手机帐号登录测试未通过!!!!");
		}
		
	}
	
	static void regist(){
		UserVO userVO = new UserVO();
		userVO.setCode("colin");
		userVO.setEmail("41987539@qq.com");
		userVO.setPassword("hello");
		userVO.setPhone("13488888888");
		userVO.setQuota(10.00);
//		userVO.setRegisterTime("2013-01-13 10:10:10");
		userVO.setScore(100);
		userVO.setUserName("林木");
		userVO.setDefaultRegionId(1);
		userVO.setDefaultAddress("深圳福田景田区香丽大厦");
		userVO.setUserType(1);
		ResultVO result = action.regist(userVO);
		if (result.isSuccess()) {
			System.out.println("【注册测试通过】");
		}else{
			System.out.println("!!!!注册测试未通过!!!!");
		}
	}
	
	static void sendValidateEmail(){
		ResultVO result = action.sendValidateEmail("colin");
		if (result.isSuccess()) {
			System.out.println("【邮件发送测试通过】");
		}else{
			System.out.println("!!!!邮件发送测试未通过!!!!");
		}
	}

	private static final long serialVersionUID = 2399166345711874635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getRequestURI().endsWith("loginTest")){
			login();
		}else if(req.getRequestURI().endsWith("registTest")){
			regist();
		}else if(req.getRequestURI().endsWith("sendEmailTest")){
			sendValidateEmail();
		}
	}
}
