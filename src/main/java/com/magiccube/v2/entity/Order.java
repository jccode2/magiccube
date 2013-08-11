package com.magiccube.v2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends IdEntity {

	private String createTime;
	private Integer userId;
	private String userRemark;
	private String address;
	private String contact;
	private String phone;
	private String orderStatus;
	private Double totalPrice;
	private Double actuallyPrice;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getActuallyPrice() {
		return actuallyPrice;
	}

	public void setActuallyPrice(Double actuallyPrice) {
		this.actuallyPrice = actuallyPrice;
	}

}
