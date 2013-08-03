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
	 * 联系人
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
	
	//优惠编码 ,为3位2进制数。'0'表示无优惠，'1'表示有优惠
	//自右到左：第一位表示首次订餐优惠，第二位表示订餐5份以上优惠，第三位表示10点半前下单优惠
	//例如"001"表示只有首次订餐优惠
	private String discountCode;
	
	private String exceptionDesc;
	
	/**
	 * 备注信息
	 */
	private String userRemark;

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

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public int getFoundAmount() {
		int amount = 0;
		for(PlateVO plate : plateList) {
			for(OrderFoodView orderFood : plate.getFoodList()) {
				amount += orderFood.getAmount();
			}
		}
		return amount;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
	
}
