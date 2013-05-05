package com.magiccube.food.model;


public class PackageItemVO extends FoodVO {

	private int packageId;
	private int foodId;

	public PackageItemVO() {
	}

	public PackageItemVO(int foodId) {
		this.foodId = foodId;
	}

	public PackageItemVO(int foodId, int packageId) {
		this(foodId);
		this.packageId = packageId;
	}


	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	

}
