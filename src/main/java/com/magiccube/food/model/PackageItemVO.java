package com.magiccube.food.model;

import com.magiccube.core.base.model.BaseVO;

public class PackageItemVO extends BaseVO {

	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
