/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.packages.dao;

import java.util.List;

import com.magiccube.core.base.dao.BaseDAO;
import com.magiccube.packages.model.PackageGroupVO;
import com.magiccube.packages.model.PackageVO;

/**
 * 套餐DAO
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class PackageDAO extends BaseDAO{
	
	public void insertPackage(PackageVO packageVO) {
		this.sqlSessionTemplate.insert("com.magiccube.packages.insertPackage", packageVO);
	}
	
	public int queryIdForPackageVO(PackageVO packageVO) {
		return (Integer)this.sqlSessionTemplate.selectOne("com.magiccube.packages.queryIdForPackageVO", packageVO);
	}
	
	public void insertPackageReShop(PackageVO packageVO){
		this.sqlSessionTemplate.insert("com.magiccube.packages.insertPackageReShop", packageVO);
	}
	
	public void insertPackageGroup(PackageGroupVO packageGroupVO){
		this.sqlSessionTemplate.insert("com.magiccube.packages.insertPackageGroup",packageGroupVO);
	}
	
	public List<PackageVO> queryPackagesByShopId(int shopId){
		List<PackageVO> result =  this.sqlSessionTemplate.selectList("com.magiccube.packages.queryPackagesByShopId", shopId);
		return result;
	}
	
	public int queryCountOfPackage(){
		return (Integer)this.sqlSessionTemplate.selectOne("com.magiccube.packages.queryCountOfPackage");
	}

}
