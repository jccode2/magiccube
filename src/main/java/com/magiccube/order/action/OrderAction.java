/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magiccube.config.action.ConfigAction;
import com.magiccube.core.base.action.BaseAction;
import com.magiccube.core.base.model.ResultVO;
import com.magiccube.order.model.OrderConstants;
import com.magiccube.order.model.OrderQueryCondition;
import com.magiccube.order.model.OrderVO;
import com.magiccube.order.model.OrderVOWithFood;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.model.SuggestVO;
import com.magiccube.order.service.OrderService;
import com.magiccube.order.util.OrderUtils;
import com.magiccube.user.model.UserVO;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2012-12-18 Xingling build
 */
@Controller
@RemoteProxy
public class OrderAction extends BaseAction {

	final static Logger LOGGER = LoggerFactory.getLogger(OrderAction.class);

//	static ApplicationContext context = null;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ConfigAction configAction;

//	static {
//		context = ApplicationContextInitor.getContext();
//		orderService = (OrderService) context.getBean("OrderService");
//	}

	/**
	 * 提交订单
	 * 
	 * @param orderVO
	 *            订单VO
	 * @return 提交是否成功
	 */
	@RemoteMethod
	public ResultVO submitOrder(OrderVO orderVO,HttpServletRequest request) {
		try {
			UserVO currentUser = (UserVO) request.getSession().getAttribute("current_user");
			orderService.submitOrder(orderVO,currentUser);
		} catch (Exception e) {
			LOGGER.error("submitOrder 出错：" + e);
			return new ResultVO("提交订单时后台出现错误" + e);
		}
		return new ResultVO("订餐成功！");
	}
	

	/**
	 * 根据用户id查询未处理订单
	 * 
	 * @param userId
	 *            用户id
	 * @return 未处理的订单对象集合
	 */
	@RemoteMethod
	public List<OrderVO> queryUnEvaluateOrder(int userId) {
		try {
			return orderService.queryUnEvaluateOrder(userId);
		} catch (Exception e) {
			LOGGER.error("queryUnEvaluateOrder 出错：" + e);
			return null;
		}
	}

	/**
	 * 更新order的状态
	 * @param id 订单id
	 * @param status 订单状态
	 * @return
	 */
	public boolean updateOrderStatus(int id, int status) {
		return orderService.updateOrderStatus(id, status);
	}

	/**
	 * 根据条件查询订单列表
	 */
	@RemoteMethod
	public List<OrderVOWithFood> queryNewOrdersWithFood(
			OrderQueryCondition queryCondition) {
		// 当前用户ID, 店铺ID, 应该可以在全局进行查找, 这里暂时写死.
		int currUserId = 3, currShopId = 1;

		queryCondition.setUserId(currUserId);
		queryCondition.setShopId(currShopId);
		queryCondition.addStatus(OrderVO.ORDER_STATUS_NEW);

		return orderService.queryOrdersWithFood(queryCondition);
	}

	/**
	 * 查询订单列表
	 * 
	 * @param queryCondition
	 *            查询条件
	 * @return List<OrderView>
	 */
	public List<OrderView> queryNewOrderViewList(
			OrderQueryCondition queryCondition) {
		List<OrderVOWithFood> list0 = queryNewOrdersWithFood(queryCondition);
		List<OrderView> list = OrderUtils.transferOrderVOToView(list0);
		return list;
	}

	/**
	 * 根据条件查询历史订单列表,分页方式
	 * 
	 */
	public List<OrderVOWithFood> queryHistoryOrdersWithFoodByPage(
			OrderQueryCondition queryCondition, int pageNo, int pageSize) {
		// 当前用户ID, 店铺ID, 应该可以在全局进行查找, 这里暂时写死.
		int currUserId = 3, currShopId = 1;

		queryCondition.setPageNo(pageNo);
		queryCondition.setPageSize(pageSize);
		queryCondition.setUserId(currUserId);
		queryCondition.setShopId(currShopId);

		return orderService.queryOrdersWithFoodByPage(queryCondition);
	}

	public List<OrderView> queryHistoryOrderViewList(
			OrderQueryCondition queryCondition) {
		List<OrderVOWithFood> list0 = queryHistoryOrdersWithFoodByPage(
				queryCondition, queryCondition.getPageNo(),
				queryCondition.getPageSize());
		List<OrderView> list = OrderUtils.transferOrderVOToView(list0);
		return list;
	}

	/**
	 * 查询历史订单的总数
	 */
	public int queryHistoryOrdersCount(OrderQueryCondition queryCondition) {
		// 当前用户ID, 店铺ID, 应该可以在全局进行查找, 这里暂时写死.
		int currShopId = 1;
		queryCondition.setShopId(currShopId);
		return orderService.queryOrdersCount(queryCondition);
	}

	/**
	 * 根据订单ID获取一个订单详情(包括相应食物列表)
	 */
	public List<OrderVOWithFood> getOrderWithFood(int orderId) {
		return orderService.getOrderWithFood(orderId);
	}

	/**
	 * 获取一个订单(及其食物明细)
	 * 
	 * @param orderId
	 *            订单ID
	 * @return OrderView
	 */
	public OrderView getOrderView(int orderId) {
		return orderService.getOrderView(orderId);
	}

	/**
	 * 更新是否自动出单
	 * 
	 * @return true-更新成功; false-更新失败
	 */
	public boolean updateAutoPrint(String value) {
		int ret = configAction.updateConfig(
				OrderConstants.CONFIG_KEY_AUTO_PRINT, value);
		return ret > 0;
	}

	/**
	 * 获取是否自动出单
	 * 
	 * @return true-开启自动出单; false-关闭自动出单
	 */
	public boolean getAutoPrint() {
		String value = configAction.getConfigValue(
				OrderConstants.CONFIG_KEY_AUTO_PRINT, "false");
		return Boolean.valueOf(value).booleanValue();
	}
	
	/**
	 * 出单
	 * @param id
	 * @return
	 */
	public boolean issue(int id) {
		return orderService.issue(id);
	}
	
	/**
	 * 提交建议
	 * @param suggestVO
	 * @return
	 */
	@RemoteMethod
	public ResultVO suggest(SuggestVO suggestVO){
		try{
		 orderService.suggest(suggestVO);
		}catch(Exception e){
			e.printStackTrace();
			return new ResultVO("糟糕，服务器遇到些错误，提交没有成功*_*，请稍后再试，或通过电话23946075联系我们");
		}
		return new ResultVO();
	}
	
	/**
	 * 根据用户ID查询用户的订单
	 * @param int userId
	 * @return
	 */
	@RemoteMethod
	public List<OrderView> queryOrdersByUserId(int userId) {
		OrderQueryCondition queryCondition = new OrderQueryCondition();
		queryCondition.setUserId(userId);
		queryCondition.setPageNo(1);
		queryCondition.setPageSize(5);
		List<OrderVOWithFood> list0 = orderService.queryOrdersByUserId(queryCondition);
		List<OrderView> list = OrderUtils.transferOrderVOToView(list0);
		return list;
	}
	
	/**
	 * 获取待办数据
	 * @param shopId
	 * @return
	 */
	public int getTodoCount(int shopId) {
		return orderService.getTodoCount(shopId);
	}
}
