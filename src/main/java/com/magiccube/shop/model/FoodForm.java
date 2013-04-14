package com.magiccube.shop.model;

public class FoodForm {

	private int id;
	private String foodName;
	private String detail;
	private int type;
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String groupName) {
		this.foodName = groupName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GroupForm [id=" + id + ", groupName=" + foodName + ", detail=" + detail
				+ ", type=" + type + "]";
	}
}
