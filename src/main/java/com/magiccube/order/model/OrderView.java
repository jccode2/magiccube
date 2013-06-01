package com.magiccube.order.model;

import java.util.List;

import com.magiccube.core.base.model.BaseVO;

public class OrderView extends BaseVO {
	
	/**
	 * 订单号
	 */
	private int id;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 */
	private String contact;
	
	/**手机*/
	private String phone;
	
	/**
	 * 总价格
	 */
	private double totalPrice;
	
	/**
	 * 实际应付总价
	 */
	private double actuallyPrice;
	
	/**
	 * 食品总数
	 */
//	private int totalAmount;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 期望送达时间
	 */
	private String exceptTime;
	
	/**
	 * 餐盘列表
	 */
	private List<PlateVO> plateList;
	
	/**
	 * 订单状态: 0:new 1:dealed 2:exception 3:evaluated
	 */
	private int orderStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * 获取餐盘总数
	 * @return
	 */
	public int getPlateAmout() {
		return this.plateList.size();
	}

	/**
	 * 获取食品总数
	 * @return
	 */
	public int getTotalAmount() {
		int count = 0;
		for(PlateVO plate : plateList) {
			count += plate.getFoodList().size();
		}
		return count;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExceptTime() {
		return exceptTime;
	}

	public void setExceptTime(String exceptTime) {
		this.exceptTime = exceptTime;
	}

	public List<PlateVO> getPlateList() {
		return plateList;
	}

	public void setPlateList(List<PlateVO> plateList) {
		this.plateList = plateList;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getActuallyPrice() {
		return actuallyPrice;
	}

	public void setActuallyPrice(double actuallyPrice) {
		this.actuallyPrice = actuallyPrice;
	}
}
