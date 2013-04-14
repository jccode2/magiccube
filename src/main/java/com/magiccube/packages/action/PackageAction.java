/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.packages.action;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import com.magiccube.core.base.model.ResultVO;
import com.magiccube.core.springmvc.ApplicationContextInitor;
import com.magiccube.packages.model.PackageGroupVO;
import com.magiccube.packages.model.PackageVO;
import com.magiccube.packages.service.PackageService;

/**
 * 套餐action
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
@RemoteProxy
public class PackageAction extends ApplicationObjectSupport{
	
	final static Logger LOGGER = LoggerFactory.getLogger(PackageAction.class);
	
	static ApplicationContext context = null;
	
	static PackageService packageService = null;
	
	static{
		context = ApplicationContextInitor.getContext();
		packageService = (PackageService)context.getBean("PackageService");
	}
	
	/**
	 * 增加套餐（同时指定到店铺）
	 * @param LoginVO
	 */
	public ResultVO insertPackage(PackageVO packageVO){
		try{
			packageService.insertPackage(packageVO);
		}catch(Exception e){
			LOGGER.info("insertPackage 出错："+e);
			return new ResultVO(e.toString());
		}
		return new ResultVO();
	}
	
	/**
	 * 增加套餐分组
	 * @param packageGroupVO
	 */
	public ResultVO insertPackageGroup(PackageGroupVO packageGroupVO){
		try{
			packageService.insertPackageGroup(packageGroupVO); 
		}catch(Exception e){
			LOGGER.info("insertPackageGroup 出错："+e);
			return new ResultVO(e.toString());
		}
		return new ResultVO();
	}
	
	/**
	 * 查询所有在架套餐
	 * @return List<PackageVO>
	 */
	@RemoteMethod
	public List<PackageVO> queryPackages(){
		List<PackageVO> result = packageService.queryPackages();
		return result;
	}
	
}
