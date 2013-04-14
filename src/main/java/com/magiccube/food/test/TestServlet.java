package com.magiccube.food.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magiccube.core.base.model.ResultVO;
import com.magiccube.food.action.FoodAction;
import com.magiccube.food.model.FoodVO;

public class TestServlet extends HttpServlet {

	static FoodAction action = new FoodAction();

	/**
	 * FOR TEST
	 * init test datas
	 */
	public static void insertFood() {
		FoodVO foodVO = new FoodVO();
		String code = "2013F-00106-00001";
		foodVO.setCode(code);
		foodVO.setCurrentPrice(3.0);
		foodVO.setDetail("香喷喷，好吃又好看的西红柿炒鸡蛋");
		foodVO.setDroped(false);
		foodVO.setFoodName("西红柿炒鸡蛋");
		foodVO.setGroupId(1);
		foodVO.setImage("/web/images/" + code + ".png");
		foodVO.setOriginPrice(4.0);
		foodVO.setShopId(1);
		foodVO.setStock(10);
//		ResultVO result = action.insertFood(foodVO);
//		System.out.println(result.isSuccess());
	}

	public static void queryFoods() {
		List<FoodVO> result = action.queryFoods();
		System.out.println(result.get(0).getFoodName());
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
		if(req.getRequestURI().contains("queryFoods")){
			queryFoods();
		}else if(req.getRequestURI().contains("insertFood")){
			insertFood();
		}
	}
}
