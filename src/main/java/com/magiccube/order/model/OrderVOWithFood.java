package com.magiccube.order.model;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class OrderVOWithFood extends OrderVO {

	private int foodId;

	private String foodName;

	private String foodImage;
	
	private int amount;
	
	private int plate;
	
	private double price;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int food_id) {
		this.foodId = food_id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String food_name) {
		this.foodName = food_name;
	}

	public String getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(String food_image) {
		this.foodImage = food_image;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPlate() {
		return plate;
	}

	public void setPlate(int plate) {
		this.plate = plate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
