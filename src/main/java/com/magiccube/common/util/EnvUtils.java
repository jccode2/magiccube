package com.magiccube.common.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.user.model.UserVO;

public class EnvUtils {

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}
	
	public static UserVO getUser() {
		EnvironmentInfoVO envInfo = (EnvironmentInfoVO)session().getAttribute("environmentInfo");
		if(envInfo != null) 
			return envInfo.getUser();
		return null;
	}
}
