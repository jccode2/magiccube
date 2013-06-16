/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.order.dao;

import java.util.List;

import com.magiccube.core.base.dao.BaseDAO;
import com.magiccube.order.model.OrderItemVO;
import com.magiccube.order.model.OrderQueryCondition;
import com.magiccube.order.model.OrderVO;
import com.magiccube.order.model.OrderVOWithFood;
import com.magiccube.order.model.SuggestVO;

/**
 * @author Xingling
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class OrderDAO extends BaseDAO {

	/**
	 * 插入Order数据到数据库
	 * @param orderVO
	 */
	public int insertOrder(OrderVO orderVO) {
		this.sqlSessionTemplate.insert("com.magiccube.order.insertOrder", orderVO);
		return orderVO.getId();
	}
	
	/**
	 * 插入OrderItem数据到数据库
	 * @param orderItemVO
	 */
	public void insertOrderItem(OrderItemVO orderItemVO) {
		this.sqlSessionTemplate.insert("com.magiccube.order.insertOrderItem", orderItemVO);
	}
	
	/**
	 * 更新Order的状态
	 * @param orderVO
	 */
	public int updateOrderStatus(OrderVO orderVO) {
		return this.sqlSessionTemplate.update("com.magiccube.order.updateOrderStatus", orderVO);
	}
	
	/**
	 * 根据用户id查询未处理订单
	 * @param userId
	 * @return 未处理的订单对象集合
	 */
	public List<OrderVO> queryUnEvaluateOrder(int userId){
		List<OrderVO> result =  this.sqlSessionTemplate.selectList("com.magiccube.order.queryUnEvaluateOrder", userId);
		return result;
	}

	/**
	 * 根据条件查询订单列表
	 * @param map 查询条件
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersWithFood(OrderQueryCondition params) {
		List<OrderVOWithFood> result = this.sqlSessionTemplate.selectList("com.magiccube.order.queryOrdersWithFood", params);
		return result;
	}
	
	/**
	 * 根据条件查询订单列表,分页方式
	 * @param map 查询条件
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersWithFoodByPage(OrderQueryCondition params) {
		List<OrderVOWithFood> result = this.sqlSessionTemplate.selectList("com.magiccube.order.queryOrdersWithFoodByPage", params);
		return result;
	}
	
	/**
	 * 根据条件查询订单列表的数量
	 * @param params
	 * @return
	 */
	public int queryOrdersCount(OrderQueryCondition params) {
		int count = (Integer)this.sqlSessionTemplate.selectOne("com.magiccube.order.queryOrdersCount", params);
		return count;
	}
	
	/**
	 * 根据订单ID获取一个订单详情(包括相应食物列表)
	 * @param orderId
	 * @return
	 */
	public List<OrderVOWithFood> getOrderWithFood(int orderId) {
		List<OrderVOWithFood> result = this.sqlSessionTemplate.selectList("com.magiccube.order.getOrderWithFood", orderId);
		return result;
	}
	
	/**
	 * 根据订单ID获取提交者的userName
	 * @param orderId
	 * @return
	 */
	public String getOrderUser(int orderId) {
		String result = (String)this.sqlSessionTemplate.selectOne("com.magiccube.order.getOrderUser", orderId);
		return result;
	}

	public void insertSuggest(SuggestVO suggestVO) {
		this.sqlSessionTemplate.insert("com.magiccube.order.insertSuggest", suggestVO);
	}
	
	/**
	 * 根据用户ID查询用户的订单
	 * @param params
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersByUserId(OrderQueryCondition params) {
		List<OrderVOWithFood> result = this.sqlSessionTemplate.selectList("com.magiccube.order.queryOrdersByUserId", params);
		return result;
	}
	
	/**
	 * 获取待办数据
	 * @param shopId
	 * @return
	 */
	public int getTodoCount(int shopId) {
		int ret = (Integer)this.sqlSessionTemplate.selectOne("com.magiccube.order.getTodoCount", shopId);
		return ret;
	}
}
