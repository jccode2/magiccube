package com.magiccube.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.magiccube.core.base.action.QueryForm;

public class OrderQueryForm extends QueryForm {

	private String keyword;
	private int overtime;
	private String orderStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "QueryForm [pageNo=" + getPageNo() + ", pageSize=" + getPageSize()
				+ ", recordCount" + getRecordCount() + ", keyword=" + keyword 
				+ ", overtime=" + overtime + ", orderStatus=" + orderStatus
				+ "]";
	}
}
