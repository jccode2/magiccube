package com.magiccube.order.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magiccube.order.model.OrderVO;

@Controller
public class OrderSubmitTest extends OrderStatePublisher {

	@RequestMapping("/test/autoprint")
	public void testAutoprint() {
		OrderVO vo = new OrderVO();
		vo.setId(90); 
		vo.setPhone("8505479");
		vo.setTotalPrice(15.0);
		
		submitOrder(vo);
		notify(vo);
	}
	
	/**
	 * 提交订单
	 * @param orderVO 订单VO
	 */
	public void submitOrder(OrderVO orderVO) {
		System.out.println("提交订单了...");
		System.out.println("订单是: " + orderVO);
	}

}
