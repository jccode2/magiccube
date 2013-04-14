/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.address.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.magiccube.address.model.AddressVO;
import com.magiccube.address.model.RegionVO;
import com.magiccube.address.service.AddressService;
import com.magiccube.core.base.action.BaseAction;
import com.magiccube.core.base.model.ResultVO;
import com.magiccube.core.springmvc.ApplicationContextInitor;

/**
 * Address模块，处理区域、地址等信息
 * 
 * @author Xingling
 * @since JDK1.6
 * @history 2012-12-18 Xingling build
 */
@RemoteProxy
public class AddressAction extends BaseAction {
	
	final static Logger LOGGER = LoggerFactory.getLogger(AddressAction.class);
	
	static ApplicationContext context = null;
	
	static AddressService addressService = null;
	
	static{
		context = ApplicationContextInitor.getContext();
		addressService = (AddressService)context.getBean("AddressService");
	}
	
	/**
	 * 根据当前区域Id查询子区域
	 * @param currentRegionId
	 * @return 所有子区域
	 */
	@RemoteMethod
	public List<RegionVO> queryChildRegion(int currentRegionId){
		List<RegionVO> result = null;
		try{
			result = addressService.queryChildRegion(currentRegionId);
		}catch(Exception e){
			LOGGER.error("queryChildRegion 出错："+e);
		}
		return result;
	}
	
	/**
	 * 根据用户输出文本，查询包含该文本的零散送餐地址
	 * @param userInput
	 * @return 所有符合条件的地址
	 */
	@RemoteMethod
	public List<AddressVO> queryAddress(String userInput){
		List<AddressVO> result = null;
		try{
			result = addressService.queryAddress(userInput);
		}catch(Exception e){
			LOGGER.error("queryAddress 出错："+e);
		}
		return result;
	}
	
	/**
	 * 将用户输入的建议送餐地址添加到临时记录中，以供业务参考
	 * @param tempAddress
	 * @param phone
	 * @return 执行结果
	 */
	@RemoteMethod
	public ResultVO addTempAddress(String tempAddress ,String phone){
		try{
			addressService.addTempAddress(tempAddress,phone);
		}catch(Exception e){
			LOGGER.error("addTempAddress 出错："+e);
			return new ResultVO(false,"增加建议送餐地址时出现异常");
		}
		return new ResultVO();
	}
	
}
