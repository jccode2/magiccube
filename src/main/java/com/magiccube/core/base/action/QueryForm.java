package com.magiccube.core.base.action;

/**
 * 查询Form 基类
 * @author jcchen
 *
 */
public class QueryForm {

	/** 默认列表每页的数目 */
	public static final int SIZE_DEFAULT = 18;
	
	/** 页码 */
	private int pageNo = 1;
	
	/** 每页记录的条数 */
	private int pageSize = 6;
	
	/** 总的记录条数 */
	private int recordCount;
	
	/**
	 * 获取页码
	 * @return int 页码
	 */
	public int getPageNo() {
		if(pageNo > getPageCount())
			return 1;
		return pageNo;
	}
	
	/**
	 * 设置页码
	 * @param pageNo 页码
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	/**
	 * 获取每页的条数
	 * @return int 每页的条数
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页的条数
	 * @param pageSize int 每页的条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取记录的条数 
	 * @return int 记录条数 
	 */
	public int getRecordCount() {
		return recordCount;
	}
	
	/**
	 * 设置总记录条数
	 * @param recordCount int 记录条数
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	/**
	 * 获取有多少页
	 * @return int 页数
	 */
	public int getPageCount() {
		int c = recordCount / pageSize;
		return recordCount % pageSize == 0 ? c : c+1;
	}
}
