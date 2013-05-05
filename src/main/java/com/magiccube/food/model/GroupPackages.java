/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.food.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since 2013-5-5
 * @author ksfifa
 */
public class GroupPackages extends FoodGroupVO {

	private List<PackageVO> packageList = new ArrayList<PackageVO>();

	public List<PackageVO> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<PackageVO> packageList) {
		this.packageList = packageList;
	}

	public void add(PackageVO packageVO) {
		this.packageList.add(packageVO);
	}

	public void remove(PackageVO packageVO) {
		this.packageList.remove(packageVO);
	}

}
