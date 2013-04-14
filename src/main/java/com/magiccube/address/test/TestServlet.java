/******************************************************************************
 * Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.address.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magiccube.address.action.AddressAction;
import com.magiccube.address.model.AddressVO;
import com.magiccube.address.model.RegionVO;
import com.magiccube.core.base.model.ResultVO;

/**
 * @author Xingling
 * @since jdk6.0
 * @version 2012-12-16 Xingling build
 */
public class TestServlet extends HttpServlet {

	static AddressAction action = new AddressAction();

	public static void queryChildRegion(int currentRegionId) {
		List<RegionVO> result = action.queryChildRegion(currentRegionId);
		for (RegionVO test : result) {
			System.out.println(test.getFullPath());
		}
	}

	public static void queryAddress(String context) {
		List<AddressVO> result = action.queryAddress(context);
		for (AddressVO test : result) {
			System.out.println(test.getFullName());
		}
	}
	

	public static void addTempAddress(String tempAddress ,String phone){
		ResultVO result = action.addTempAddress(tempAddress, phone);
		System.out.println(result.isSuccess());
	}

	private static final long serialVersionUID = 2399166345711874635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getRequestURI().contains("queryChildRegion")) {
			queryChildRegion(1);
		} else if (req.getRequestURI().contains("queryAddress")) {
			queryAddress("景秀");
		} else if (req.getRequestURI().contains("addTempAddress")) {
			addTempAddress("景秀小学","13611112222");
		}
	}
}
