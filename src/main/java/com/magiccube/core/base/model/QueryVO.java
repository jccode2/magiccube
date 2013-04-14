package com.magiccube.core.base.model;

import com.magiccube.core.base.action.QueryForm;


/**
 * 查询条件VO
 * 
 * @author jcchen
 * 
 */
public class QueryVO {

	private int pageNo;
	private int pageSize;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageFrom() {
		return (pageNo - 1) * pageSize;
	}
	
	public void setPage(QueryForm queryForm) {
		this.setPageNo(queryForm.getPageNo());
		this.setPageSize(queryForm.getPageSize());
	}
}
