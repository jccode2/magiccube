package com.magiccube.order.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magiccube.core.util.tools.JsonUtil;
import com.magiccube.core.util.tools.PosPrinter;
import com.magiccube.order.action.OrderAction;
import com.magiccube.order.model.OrderVO;
import com.magiccube.order.model.OrderView;
import com.magiccube.order.service.OrderWebSocketServlet;

@Component
public class OrderAutoPrintListener implements OrderStateListener {
	
	final static Logger LOGGER = LoggerFactory.getLogger(OrderAutoPrintListener.class);
	
	@Autowired
	private OrderAction orderAction;

	@Override
	public void orderSubmited(OrderVO orderVO) {
		
		OrderView orderView = orderAction.getOrderView(orderVO.getId());
		LOGGER.debug("提交了订单"+orderView+";");
		
		if(orderAction.getAutoPrint()) { //自动出单
			LOGGER.debug("开始自动出单");
			PosPrinter.print(orderView);
			
		} else { // websocket推送刷新列表; 只有不开启"自动出单"时,才会通知列表页面刷新.
			String json = JsonUtil.objectToJson(orderView);
			OrderWebSocketServlet.broadcast(json);
		}
		
		// 更新食物库存信息
		
	}

}
