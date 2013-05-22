/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.order.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.magiccube.config.action.ConfigAction;
import com.magiccube.core.base.action.BaseAction;
import com.magiccube.core.base.model.ResultVO;
import com.magiccube.core.springmvc.ApplicationContextInitor;
import com.magiccube.core.util.tools.DateTimeFormatUtils;
import com.magiccube.order.model.OrderFoodView;
import com.magiccube.order.model.OrderQueryCondition;
import com.magiccube.order.model.OrderVO;
import com.magiccube.order.model.OrderVOWithFood;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.model.PlateVO;
import com.magiccube.order.model.OrderConstants;
import com.magiccube.order.service.OrderService;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2012-12-18 Xingling build
 */
@Controller
@RemoteProxy
public class OrderAction extends BaseAction {

	final static Logger LOGGER = LoggerFactory.getLogger(OrderAction.class);

	static ApplicationContext context = null;

	static OrderService orderService = null;

	@Autowired
	private ConfigAction configAction;

	static {
		context = ApplicationContextInitor.getContext();
		orderService = (OrderService) context.getBean("OrderService");
	}

	/**
	 * 提交订单
	 * 
	 * @param orderVO
	 *            订单VO
	 * @return 提交是否成功
	 */
	@RemoteMethod
	public ResultVO submitOrder(OrderVO orderVO) {
		try {
			Calendar objCal = Calendar.getInstance();
			switch (orderVO.getExceptTimeType()) {
			case 0: // 12点送达
				objCal.set(Calendar.HOUR_OF_DAY, 12);
				objCal.set(Calendar.MINUTE, 0);
				objCal.set(Calendar.SECOND, 0);
				break;
			case 1: // 下午6点送达
				objCal.set(Calendar.HOUR_OF_DAY, 18);
				objCal.set(Calendar.MINUTE, 0);
				objCal.set(Calendar.SECOND, 0);
				break;
			case 2: // 30分钟后送达
				objCal.set(Calendar.MINUTE, objCal.get(Calendar.MINUTE) + 30);
				break;
			case 3: // 1小时后送达
				objCal.set(Calendar.HOUR_OF_DAY,
						objCal.get(Calendar.HOUR_OF_DAY) + 1);
				break;
			}
			orderVO.setExceptTime(objCal.getTime());
			orderVO.setCreateTime(new Date());
			orderVO.setShopId(1);
			orderService.submitOrder(orderVO);
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
	 * 更新Order的状态
	 * 
	 * @param orderVO
	 *            订单VO
	 */
	public int updateOrderStatus(OrderVO orderVO) {
		return orderService.updateOrderStatus(orderVO);
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
		List<OrderView> list = transferOrderVOToView(list0);
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
		List<OrderView> list = transferOrderVOToView(list0);
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
		List<OrderVOWithFood> list = this.getOrderWithFood(orderId);
		List<OrderView> ret = transferOrderVOToView(list);
		return ret.size() > 0 ? ret.get(0) : null;
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
	 * 将List<OrderVOWithFood>转换为List<OrderViewVO>
	 * 
	 * @param list
	 *            List<OrderVOWithFood>
	 * @return List<OrderViewVO>
	 */
	private List<OrderView> transferOrderVOToView(List<OrderVOWithFood> list) {
		List<OrderView> ret = new ArrayList<OrderView>();
		int oldOrderId = 0, oldPlate = 0;
		OrderView view = null;
		List<PlateVO> plateList = null;
		PlateVO plate = null;
		for (OrderVOWithFood vo : list) {
			if (oldOrderId != vo.getId()) { // new
				// reset
				oldOrderId = vo.getId();
				oldPlate = 0;

				if (view != null)
					ret.add(view); // add the prior one
				view = new OrderView();
				view.setId(vo.getId());
				view.setAddress(vo.getAddress());
				view.setContact(vo.getContact());
				view.setCreateTime(DateTimeFormatUtils.formatDateTime(vo
						.getCreateTime()));
				view.setExceptTime(DateTimeFormatUtils.formatDateTime(vo
						.getExceptTime()));
				view.setOrderStatus(vo.getOrderStatus());
				view.setPhone(vo.getPhone());
				view.setTotalPrice(vo.getTotalPrice());

				plateList = new ArrayList<PlateVO>();
				view.setPlateList(plateList);
			}

			if (oldPlate != vo.getPlate()) {
				oldPlate = vo.getPlate();
				plate = new PlateVO(vo.getPlate());
				plateList.add(plate);
			}

			OrderFoodView foodView = new OrderFoodView();
			foodView.setId(vo.getFoodId());
			foodView.setFood(vo.getFoodName());
			foodView.setAmount(vo.getAmount());
			foodView.setPrice(vo.getPrice());
			plate.addFood(foodView);
		}
		if (view != null) { // add the last one
			ret.add(view);
		}
		return ret;
	}

}
