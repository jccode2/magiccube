/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.packages.model;

import com.magiccube.core.base.model.BaseVO;

/**
 * 套餐
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public class PackageVO extends BaseVO {
	
	private int id;

	private String code;
	
	private String packageName;
	
	private String detail;
	
	private String image;
	
	private double originPrice;
	
	private double currentPrice;
	
	private int stock;
	
	private int shopId;
	
	private boolean deleted;
	
	private int groupId;
	
	private String groupName;
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param packageName the package_name to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * @return the originPrice
	 */
	public double getOriginPrice() {
		return originPrice;
	}

	/**
	 * @param originPrice the originPrice to set
	 */
	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}

	/**
	 * @return the currentPrice
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice the currentPrice to set
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * @return the shopId
	 */
	public int getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}


	/**
	 * @return the deleted
	 */
	public boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer(128);
		sb.append("PackageVO=\n");
		sb.append("code:"+code+"\n");
		sb.append("packageName:"+packageName+"\n");
		sb.append("detail:"+detail+"\n");
		
		return sb.toString();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
