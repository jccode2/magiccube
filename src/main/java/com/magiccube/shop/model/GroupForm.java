package com.magiccube.shop.model;

public class GroupForm {

	private int id;
	private String groupName;
	private String detail;
	private int type;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GroupForm [id=" + id + ", groupName=" + groupName + ", detail=" + detail
				+ ", type=" + type + "]";
	}
}
