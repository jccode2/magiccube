package com.magiccube.stat.service;

import java.util.Date;
import java.util.List;

import com.magiccube.core.base.service.BaseService;
import com.magiccube.stat.dao.StatDAO;
import com.magiccube.stat.model.QuantityPerFoodVO;

public class StatService extends BaseService {

	private StatDAO statDAO;
	
	public StatDAO getStatDAO() {
		return statDAO;
	}

	public void setStatDAO(StatDAO statDAO) {
		this.statDAO = statDAO;
	}
	
	public List<QuantityPerFoodVO> queryQuantityPerFood(int shopId, Date startTime, Date endTime) {
		return statDAO.queryQuantityPerFood(shopId, startTime, endTime);
	}
}
