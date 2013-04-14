package com.magiccube.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.magiccube.core.base.action.QueryForm;

public class OrderQueryForm extends QueryForm {

	private String keyword;
	private int overtime;
	private String orderStatus;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getOvertime() {
		return overtime;
	}
	
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<Integer> getOrderStatusList() {
		List<Integer> ret = new ArrayList<Integer>();
		if(this.orderStatus == null) return ret;
		String[] status = this.orderStatus.split(",");
		for(String item : status) {
			ret.add(Integer.parseInt(item));
		}
		return ret;
	}
	@Override
	public String toString() {
		return "QueryForm [pageNo=" + getPageNo() + ", pageSize=" + getPageSize()
				+ ", recordCount" + getRecordCount() + ", keyword=" + keyword 
				+ ", overtime=" + overtime + ", orderStatus=" + orderStatus
				+ "]";
	}
}
