package com.magiccube.food.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含分组及食物的对象.<br>
 * 
 * 包含以下的映射关系 group -> foodList<FoodVO>
 * @author jcchen
 *
 */
public class GroupFoods extends FoodGroupVO {
	
	private List<FoodVO> foodList = new ArrayList<FoodVO>();

	public List<FoodVO> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodVO> foodList) {
		this.foodList = foodList;
	}
	
	public void addFood(FoodVO foodVO) {
		foodList.add(foodVO);
	}
	
	public boolean removeFood(FoodVO foodVO) {
		return foodList.remove(foodVO);
	}

	@Override
	public String toString() {
		super.toString();
		return "GroupFoods [foodList=" + foodList + "]";
	}
}
