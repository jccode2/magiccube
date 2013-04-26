package com.magiccube.weixin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/weixin")
public class MessageHandler {
	
	@RequestMapping("/test")
	public ModelAndView setMenu(HttpServletResponse response,@CookieValue(value="USERID",defaultValue="0") String userId) {
		if(userId.equals("0")){
			System.out.println("userId is null");
			Cookie cookie = new Cookie("USERID", String.valueOf(Math.random()));
			cookie.setMaxAge(3600000);
			response.addCookie(cookie);
			return new ModelAndView("forward:/web/weixinlogin.jsp");
		}else{
			System.out.println("userId is "+ userId);
			return new ModelAndView("forward:/web/weixinorder.jsp");
		}
	}
}
