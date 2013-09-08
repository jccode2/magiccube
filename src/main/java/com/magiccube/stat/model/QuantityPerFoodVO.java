package com.magiccube.stat.model;

import com.magiccube.core.base.model.BaseVO;

public class QuantityPerFoodVO extends BaseVO {

	private int foodId;
	private int groupId;
	private String foodName;
	private int amount;
	private int foodType;
	private String groupName;
	private String foodImage;
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getFoodType() {
		return foodType;
	}
	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFoodImage() {
		return foodImage;
	}
	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}
}
