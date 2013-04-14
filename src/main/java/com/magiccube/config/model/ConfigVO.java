package com.magiccube.config.model;

import com.magiccube.core.base.model.BaseVO;

public class ConfigVO extends BaseVO {
	
	public String name;
	public String value;
	public String className;
	
	public ConfigVO() {
	}
	
	public ConfigVO(String name, String value) {
		this();
		this.name = name;
		this.value = value;
		this.className = "java.lang.String"; // default value
	}

	public ConfigVO(String key, String value, String className) {
		this(key, value);
		this.className = className;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
