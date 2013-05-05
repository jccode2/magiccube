package com.magiccube.food.model;

import java.util.ArrayList;
import java.util.List;

public class PackageVO extends FoodVO {

	private List<PackageItemVO> items = new ArrayList<PackageItemVO>();
	
	public List<PackageItemVO> getItems() {
		return items;
	}

	public void setItems(List<PackageItemVO> items) {
		this.items = items;
	}
	
	public void addItem(PackageItemVO packageItem) {
		items.add(packageItem);
	}
	
	public void addItem(int foodId) {
		PackageItemVO packageItem = new PackageItemVO(foodId);
		items.add(packageItem);
	}
	
	public void addItem(String foodId) {
		this.addItem(Integer.parseInt(foodId));
	}
}
