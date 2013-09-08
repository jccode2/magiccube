package com.magiccube.stat.action;

public class FoodBean {

	private int id;
	private String foodName;
	private int type;
	private String image;
	private int amount;
	public int getId() {
		return id;
	}
	public void setId(int foodId) {
		this.id = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "FoodBean [id=" + id + ", foodName=" + foodName + ", type="
				+ type + ", image=" + image + ", amount=" + amount + "]";
	}
}
