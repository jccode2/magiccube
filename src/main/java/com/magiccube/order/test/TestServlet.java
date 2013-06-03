package com.magiccube.order.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magiccube.core.base.model.ResultVO;
import com.magiccube.core.util.tools.DataGenerator;
import com.magiccube.order.action.OrderAction;
import com.magiccube.order.model.OrderVO;

public class TestServlet extends HttpServlet {

	static OrderAction action = new OrderAction();

	public static void submitOrder(OrderVO orderVO,HttpServletRequest req) {
		orderVO.setContact("1237382832");
		ResultVO result = action.submitOrder(orderVO,req);
		System.out.println(result.isSuccess());
	}
	
	public static void queryUnEvaluateOrder(int userId,HttpServletRequest req) {
		//init datas
		OrderVO orderVO = (OrderVO)DataGenerator.get(OrderVO.class);
		action.submitOrder(orderVO,req);
		List<OrderVO> result = action.queryUnEvaluateOrder(userId);
		System.out.println(result.size());
	}

	private static final long serialVersionUID = 2399166345711874635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getRequestURI().contains("submitOrder")) {
			submitOrder(new OrderVO(),req);
		} else if (req.getRequestURI().contains("queryUnEvaluateOrder")) {
			queryUnEvaluateOrder(1,req);
		} else if (req.getRequestURI().contains("addTempAddress")) {
			//
		}
	}
}
