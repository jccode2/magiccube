package com.magiccube.v2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem extends IdEntity {

	private Integer orderId;
	private Integer itemId;
	private Integer amount;
	private Integer plate;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPlate() {
		return plate;
	}

	public void setPlate(Integer plate) {
		this.plate = plate;
	}

}
