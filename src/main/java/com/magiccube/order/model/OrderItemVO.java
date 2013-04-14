package com.magiccube.order.model;

import com.magiccube.core.base.model.BaseVO;

public class OrderItemVO extends BaseVO {

	private int id;

	private int orderId;

	private int itemId;

	private int amount;
	
	private int plate;

	private int deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}
