package com.magiccube.order.model;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;

import com.magiccube.core.base.model.BaseVO;


/**
 * plate entity
 * @author jcchen
 *
 */
@DataTransferObject
public class PlateVO extends BaseVO {

	/**
	 * plate no
	 */
	private int no;
	
	/**
	 * food list
	 */
	private List<OrderFoodView> foodList = new ArrayList<OrderFoodView>();
	
	public PlateVO() {
	}
	
	public PlateVO(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	/**
	 * plate price
	 * @return
	 */
	public double getPrice() {
		double price = 0.0;
		for(OrderFoodView food : foodList) 
			price += food.getTotalPrice();
		return price;
	}
	public List<OrderFoodView> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<OrderFoodView> foodList) {
		this.foodList = foodList;
	}
	public void addFood(OrderFoodView food) {
		foodList.add(food);
	}
	@Override
	public String toString() {
		return "PlateVO [no=" + no + ", foodList="
				+ foodList + "]";
	}
}
