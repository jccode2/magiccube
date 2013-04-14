package com.magiccube.order.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.magiccube.core.base.model.QueryVO;

public class OrderQueryCondition extends QueryVO {
	
	private int userId;
	private int shopId;
	private int itemType;
	private String keyword;
	private List<Integer> orderStatusList = new ArrayList<Integer>();
	
	public OrderQueryCondition() {
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public List<Integer> getOrderStatusList() {
		return orderStatusList;
	}
	public void setOrderStatusList(List<Integer> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}
	public boolean addStatus(Integer... status) {
		return Collections.addAll(orderStatusList, status);
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
