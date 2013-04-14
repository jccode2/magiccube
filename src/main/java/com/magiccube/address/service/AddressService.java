/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.address.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.address.dao.AddressDAO;
import com.magiccube.address.model.AddressVO;
import com.magiccube.address.model.RegionVO;
import com.magiccube.core.base.service.BaseService;

/**
 * @author Xingling
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class AddressService extends BaseService{

	final static Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	private AddressDAO addressDAO;
	
	/**
	 * 根据用户输入的关键字查询包含该关键字的AddressVO集合
	 * @param currentRegionId
	 * @return 查询结果
	 */
	public List<RegionVO> queryChildRegion(int currentRegionId) {
		return addressDAO.queryChildRegionById(currentRegionId);
	}
	

	/**
	 * 根据用户输出文本，查询包含该文本的零散送餐地址
	 * @param userInput
	 * @return 所有符合条件的地址
	 */
	public List<AddressVO> queryAddress(String userInput) {
		return addressDAO.queryAddress(userInput);
	}
	
	/**
	 * 将用户输入的建议送餐地址添加到临时记录中，以供业务参考
	 * @param tempAddress
	 * @param phone
	 * @return 执行结果
	 */
	public void addTempAddress(String tempAddress ,String phone){
		AddressVO tempAddressVO = new AddressVO();
		tempAddressVO.setFullName(tempAddress);
		//TODO getCurrentUser'id and ip,and convert time
		tempAddressVO.setPhone(phone);
		tempAddressVO.setInputTime(String.valueOf(System.currentTimeMillis()));
		addressDAO.addTempAddress(tempAddressVO);
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	
}
