package com.magiccube.stat.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.magiccube.core.base.dao.BaseDAO;
import com.magiccube.stat.model.QuantityPerFoodVO;

public class StatDAO extends BaseDAO {

	public List<QuantityPerFoodVO> queryQuantityPerFood(int shopId, Date startTime, Date endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", shopId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		List<QuantityPerFoodVO> rets = this.sqlSessionTemplate.selectList("com.magiccube.stat.quantityPerFood", params);
		return rets;
	}
}
