/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.order.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magiccube.address.model.RegionVO;
import com.magiccube.address.service.AddressService;

/**
 * 
 * @since 2013-4-20
 * @author ksfifa
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource
	AddressService addressService;

	@RequestMapping("")
	public String main(Model model) {

		List<RegionVO> addressList = addressService.queryChildRegion(-1);
		model.addAttribute("addressList", addressList);
		
		return "order/order-mian";
	}

}
