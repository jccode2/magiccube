/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.address.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.magiccube.address.model.AddressVO;
import com.magiccube.address.model.RegionVO;
import com.magiccube.core.base.dao.BaseDAO;

/**
 * @author Xingling
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class AddressDAO extends BaseDAO {

	/**
	 * 根据当前区域Id查询子区域
	 * @param currentRegionId
	 * @return 所有子区域
	 */
	public List<RegionVO> queryChildRegionById(int currentRegionId) {
		return this.sqlSessionTemplate.selectList("com.magiccube.address.queryChildRegionById", currentRegionId);
	}
	
	/**
	 * 根据用户输出文本，查询包含该文本的零散送餐地址
	 * @param userInput
	 * @return 所有符合条件的地址
	 */
	public List<AddressVO> queryAddress(String userInput){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userInput", userInput);
		return this.sqlSessionTemplate.selectList("com.magiccube.address.queryAddress",paramMap);
	}
	
	/**
	 * 将用户输入的建议送餐地址添加到临时记录中，以供业务参考
	 * @param tempAddressVO
	 */
	public void addTempAddress(AddressVO tempAddressVO){
		 this.sqlSessionTemplate.insert("com.magiccube.address.addTempAddress",tempAddressVO);
	}
}
