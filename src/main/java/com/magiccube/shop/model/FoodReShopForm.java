package com.magiccube.shop.model;

public class FoodReShopForm extends FoodForm {

	private int shopId;
	private int groupId;
	private double originPrice;
	private double currentPrice;
	private int stock;
	private boolean droped;
	private String itemIds;
	private String actionType;
	
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getFoodId() {
		return getId();
	}
	public void setFoodId(int foodId) {
		setId(foodId);
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public double getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean getDroped() {
		return droped;
	}
	public void setDroped(boolean droped) {
		this.droped = droped;
	}
	public String getItemIds() {
		return itemIds;
	}
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Override
	public String toString() {
		return "FoodReShopForm [shopId=" + shopId + ", foodId=" + getFoodId()
				+ ", groupId=" + groupId + ", originPrice=" + originPrice
				+ ", currentPrice=" + currentPrice + ", stock=" + stock
				+ ", droped=" + droped + ", itemIds=" + itemIds 
				+ ", actionType=" + actionType + "]";
	}
}
