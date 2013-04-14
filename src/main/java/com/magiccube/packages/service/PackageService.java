/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.packages.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.core.util.tools.CodeGenerator;
import com.magiccube.packages.dao.PackageDAO;
import com.magiccube.packages.model.PackageGroupVO;
import com.magiccube.packages.model.PackageVO;

/**
 * 套餐Service
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class PackageService {
	
	final static Logger LOGGER = LoggerFactory.getLogger(PackageService.class);

	private PackageDAO packageDAO;
	
	/**
	 * 增加套餐（不指定店铺）
	 * @param packageVO
	 */
	public void insertPackage(PackageVO packageVO) {
		
		//生成套餐编号
		int packageCount=packageDAO.queryCountOfPackage();
		String packageCode = CodeGenerator.getCode(CodeGenerator.TYPE.PACKAGE,packageCount);
		packageVO.setCode(packageCode);
		
		packageDAO.insertPackage(packageVO);
	}
	
	/**
	 * 增加套餐（同时指定到店铺）
	 * @param packageVO
	 */
	public void insertPackageWithShop(PackageVO packageVO) {
		
		//生成套餐编号
		int packageCount=packageDAO.queryCountOfPackage();
		String packageCode = CodeGenerator.getCode(CodeGenerator.TYPE.PACKAGE,packageCount);
		packageVO.setCode(packageCode);
		
		packageDAO.insertPackage(packageVO);
		
		//获取新生成的套餐id
		int packageId = packageDAO.queryIdForPackageVO(packageVO);
		packageVO.setId(packageId);
		
		//插入PackageReShop信息
		packageDAO.insertPackageReShop(packageVO);
	}

	/**
	 * 增加套餐分组
	 * @param packageGroupVO
	 */
	public void insertPackageGroup(PackageGroupVO packageGroupVO) {
		packageDAO.insertPackageGroup(packageGroupVO);
	}

	/**
	 * 查询所有在架套餐
	 * @return
	 */
	public List<PackageVO> queryPackages() {
		// get current shopId, then query it's packages. transiently, use default value : 1
		int shopId = 1;
		return packageDAO.queryPackagesByShopId(shopId);
	}

	public PackageDAO getPackageDAO() {
		return packageDAO;
	}

	public void setPackageDAO(PackageDAO packageDAO) {
		this.packageDAO = packageDAO;
	}

}
