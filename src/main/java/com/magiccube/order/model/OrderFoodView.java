package com.magiccube.order.model;

import com.magiccube.core.base.model.BaseVO;

public class OrderFoodView extends BaseVO {
	
	/**
	 * 食物名称
	 */
	private String food;
	
	/**
	 * 数量
	 */
	private int amount;
	
	/**
	 * 单价
	 */
	private double price;
	
	public OrderFoodView() {
	}

	public OrderFoodView(String food, int amount, double price) {
		this.food = food;
		this.amount = amount;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 获取总价
	 * @return
	 */
	public double getTotalPrice() {
		return price * amount;
	}

	@Override
	public String toString() {
		return "OrderFoodView [food=" + food + ", amount=" + amount
				+ ", price=" + price + "]";
	}
	
}
