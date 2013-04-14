package com.magiccube.core.springmvc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Xingling Chen
 * @since JDK1.6
 * @history 2012-12-18 Xingling build
 */
public class ApplicationContextInitor implements ServletContextListener  {
	private static WebApplicationContext springContext;

	public ApplicationContextInitor() {
		super();
	}

	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	public static ApplicationContext getContext() {
		return springContext;
	}
}
