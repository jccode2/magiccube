package com.magiccube.bulletin.model;

import com.magiccube.core.base.model.BaseVO;

public class BulletinVO extends BaseVO {

	private int id;
	private String content;
	private int sort;
	private boolean enable;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
