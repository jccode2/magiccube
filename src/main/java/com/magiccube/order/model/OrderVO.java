/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/

package com.magiccube.order.model;

import java.util.Date;
import java.util.List;

import com.magiccube.core.base.model.BaseVO;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-01-06 Xingling build
 */
public class OrderVO extends BaseVO {
	
	public static final int ORDER_STATUS_NEW = 0;
	public static final int ORDER_STATUS_DEALED = 1;
	public static final int ORDER_STATUS_EXCEPTION = 2;
	public static final int ORDER_STATUS_EVALUATED = 3;
	
	private int id;
	private String code;
	private int shopId;
	private Date createTime;
	private Date exceptTime;
	private int exceptTimeType;
	private int userId;
	private String userRemark;
	private String address;
	private String contact;
	private String phone;
	
	private List<OrderItemVO> foodList;
	
	//0:new
	//1:dealed
	//2:exception
	//3:evaluated
	private int orderStatus;
	
	private int managerId;
	private String managerRemark;
	private int senderId;
	private int paymentType;
	//总价
	private double totalPrice;
	//实付总价（优惠后的价格）
	private double actuallyPrice;
	//优惠编码 ,为3位2进制数。'0'表示无优惠，'1'表示有优惠
	//自右到左：第一位表示首次订餐优惠，第二位表示订餐5份以上优惠，第三位表示10点半前下单优惠
	//例如"001"表示只有首次订餐优惠
	private String discountCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExceptTime() {
		return exceptTime;
	}
	public void setExceptTime(Date exceptTime) {
		this.exceptTime = exceptTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerRemark() {
		return managerRemark;
	}
	public void setManagerRemark(String managerRemark) {
		this.managerRemark = managerRemark;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderItemVO> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<OrderItemVO> foodList) {
		this.foodList = foodList;
	}
	public int getExceptTimeType() {
		return exceptTimeType;
	}
	public void setExceptTimeType(int exceptTimeType) {
		this.exceptTimeType = exceptTimeType;
	}
	/**
	 * @return the actuallyPrice
	 */
	public double getActuallyPrice() {
		return actuallyPrice;
	}
	/**
	 * @param actuallyPrice the actuallyPrice to set
	 */
	public void setActuallyPrice(double actuallyPrice) {
		this.actuallyPrice = actuallyPrice;
	}
	/**
	 * @return the discountCode
	 */
	public String getDiscountCode() {
		return discountCode;
	}
	/**
	 * @param discountCode the discountCode to set
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	
}
