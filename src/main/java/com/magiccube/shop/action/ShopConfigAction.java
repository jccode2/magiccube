/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.shop.action;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.magiccube.config.model.ConfigVO;
import com.magiccube.config.service.ConfigService;
import com.magiccube.shop.model.BusTimeConfig;

/**
 * 店铺配置管理Action
 * 
 * @since 2013-9-3
 * @author ksfifa
 */
@Controller
@RemoteProxy
public class ShopConfigAction {
	
	@Resource
	private ConfigService configService;
	
	/**
	 * 设置营业时间
	 * 
	 * @param busTimeConfig
	 * @return
	 */
	@RemoteMethod
	public String setBusTime(BusTimeConfig busTimeConfig) {
		
		try {
			ConfigVO openTime = new ConfigVO("open-time", busTimeConfig.getOpenTime());
			configService.saveConfig(openTime);
			
			ConfigVO closingTime = new ConfigVO("closing-time", busTimeConfig.getClosingTime());
			configService.saveConfig(closingTime);
			
			ConfigVO sundayClose = new ConfigVO("sunday-close", busTimeConfig.getSundayClose());
			configService.saveConfig(sundayClose);
			
			return "设置营业时间成功";
		} catch (Exception e) {
			return "设置营业时间失败";
		}
	}
}
