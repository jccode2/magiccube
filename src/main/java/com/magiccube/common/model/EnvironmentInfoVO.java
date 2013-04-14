package com.magiccube.common.model;

import org.springframework.web.context.ContextLoader;

import com.magiccube.user.model.UserVO;

/**
 * 环境VO
 * @author Administrator
 *
 */
public class EnvironmentInfoVO {
	
	public final static String WEBROOT = ContextLoader
			.getCurrentWebApplicationContext().getServletContext()
			.getRealPath("/");
	
	private UserVO user;

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}
