/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.order.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.magiccube.core.base.service.BaseService;
import com.magiccube.core.util.tools.socket.SendPrintMessage;
import com.magiccube.food.service.FoodService;
import com.magiccube.order.dao.OrderDAO;
import com.magiccube.order.model.OrderFoodView;
import com.magiccube.order.model.OrderQueryCondition;
import com.magiccube.order.model.OrderVO;
import com.magiccube.order.model.OrderVOWithFood;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.model.PlateVO;
import com.magiccube.order.model.SuggestVO;
import com.magiccube.order.util.OrderUtils;
import com.magiccube.user.model.UserVO;
import com.magiccube.user.service.UserService;

/**
 * @author Xingling
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class OrderService extends BaseService {

	final static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	private OrderDAO orderDAO;
	
	private FoodService foodService;
	
	private UserService userService;
	
	/**
	 * 提交订单
	 * @param orderVO
	 */
	public void submitOrder(OrderVO orderVO,UserVO currentUser) {
		
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
		
		if(currentUser!=null){
			orderVO.setUserId(currentUser.getId());
		}
		
		orderDAO.insertOrder(orderVO);
		for(int i = 0;i < orderVO.getFoodList().size();++i){
			orderVO.getFoodList().get(i).setOrderId(orderVO.getId());
			orderDAO.insertOrderItem(orderVO.getFoodList().get(i));
		}
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public FoodService getFoodService() {
		return foodService;
	}

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	/**
	 * 根据用户id查询未处理订单
	 * @param userId
	 * @return 未处理的订单对象集合
	 */
	public List<OrderVO> queryUnEvaluateOrder(int userId){
		return orderDAO.queryUnEvaluateOrder(userId);
	}
	
	/**
	 * 根据条件查询订单列表
	 * @param map 查询条件
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersWithFood(OrderQueryCondition params) {
		return orderDAO.queryOrdersWithFood(params);
	}
	
	/**
	 * 根据条件查询订单列表,分页方式
	 * @param map 查询条件
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersWithFoodByPage(OrderQueryCondition params) {
		return orderDAO.queryOrdersWithFoodByPage(params);
	}
	
	/**
	 * 根据条件查询订单列表的数量
	 * @param params
	 * @return
	 */
	public int queryOrdersCount(OrderQueryCondition params) {
		return orderDAO.queryOrdersCount(params);
	}
	
	/**
	 * 更新order的状态
	 * @param id 订单id
	 * @param status 订单状态
	 * @return
	 */
	public boolean updateOrderStatus(int id, int status) {
		OrderVO vo = new OrderVO();
		vo.setId(id);
		vo.setOrderStatus(status);
		int ret = orderDAO.updateOrderStatus(vo);
		return ret > 0;
	}
	
	/**
	 * 根据订单ID获取一个订单详情(包括相应食物列表)
	 * @param orderId
	 * @return
	 */
	public List<OrderVOWithFood> getOrderWithFood(int orderId) {
		return orderDAO.getOrderWithFood(orderId);
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
		List<OrderView> ret = OrderUtils.transferOrderVOToView(list);
		return ret.size() > 0 ? ret.get(0) : null;
	}
	
	/**
	 * 出单
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean issue(int id) {
		boolean ret = this.updateOrderStatus(id, OrderVO.ORDER_STATUS_DEALED);
		if(ret) {
			OrderView orderView = getOrderView(id);
			
//			PosService.print(orderView);
			// 打印
			SendPrintMessage.vectPrintMessage.add(orderView);
			
			updateStock(orderView);
			addScoreForUser(id,orderView.getActuallyPrice());
		}
		return ret;
	}
	
	private void addScoreForUser(int id,double price) {
		String orderUser = (String) orderDAO.getOrderUser(id);
		if(orderUser!=null){
			//花了多少钱就加多少积分（临时）
			userService.addScore(orderUser, (int)price);
			System.out.println(orderUser+"/"+price);
		}
	}

	/**
	 * 更新食物库存信息
	 * @param orderView
	 */
	private void updateStock(OrderView orderView) {
		// 更新食物库存信息; 这里需要对食物的数量先进行简单的汇总,以减少IO的次数.
		Map<Integer, Integer> idAmountMap = new HashMap<Integer, Integer>();
		for(PlateVO plateVO : orderView.getPlateList()) {
			for(OrderFoodView foodView : plateVO.getFoodList()) {
				Integer foodId = foodView.getId();
				Integer amount = idAmountMap.get(foodId);
				if(amount == null) {
					idAmountMap.put(foodId, foodView.getAmount());
				} else {
					idAmountMap.put(foodId, amount + foodView.getAmount());
				}
			}
		}
		
		Set<Integer> idSet = idAmountMap.keySet();
		for(Integer id : idSet) {
			foodService.updateFoodReShopStock(id, idAmountMap.get(id));
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void suggest(SuggestVO suggestVO) {
		Calendar objCal = Calendar.getInstance();
		suggestVO.setSuggestTime(objCal.getTime());
		orderDAO.insertSuggest(suggestVO);
	}
	
	/**
	 * 根据用户ID查询用户的订单
	 * @param params
	 * @return
	 */
	public List<OrderVOWithFood> queryOrdersByUserId(OrderQueryCondition params) {
		return orderDAO.queryOrdersByUserId(params);
	}
}
