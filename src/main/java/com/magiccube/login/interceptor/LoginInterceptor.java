package com.magiccube.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.user.model.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		boolean isValid = false;
		if(session.getAttribute("environmentInfo") != null) {
			EnvironmentInfoVO env = (EnvironmentInfoVO)session.getAttribute("environmentInfo");
			UserVO user = env.getUser();
			int type = user.getUserType();
			if(type == UserVO.TYPE_ADMIN || type == UserVO.TYPE_MANAGER) {
				isValid = true;
			}
		}
		if(!isValid) {
			// redirect to index
			request.getRequestDispatcher("/").forward(request, response);
		}
		return isValid;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

}
