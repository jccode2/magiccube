package com.magiccube.v2.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magiccube.v2.entity.Order;
import com.magiccube.v2.service.OrderService;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {
	
	@Autowired
	private OrderService orderService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value = "today")
	public String today(Model model, ServletRequest request) {
		String date = sdf.format(new Date());
		model.addAttribute("orders",orderService.findAllByDateString(date.substring(0,10)+"%"));
		return "orders/view";
	}
	
	@RequestMapping(value = "month")
	public String month(Model model, ServletRequest request) {
		Calendar time = Calendar.getInstance();
		List<String> labels = new ArrayList<String>(32);
		List<Integer> counts = new ArrayList<Integer>(32);
		List<Double> totalPrices = new ArrayList<Double>(32);
		for(int i = 1;i<=time.getMaximum(Calendar.DATE);i++){
			time.set(Calendar.DATE, i);
			labels.add(String.valueOf(i));
			List<Order> orders = orderService.findAllByDateString(sdf.format(time.getTime())+"%");
			//订单数量
			counts.add(orders.size());
			totalPrices.add(countPrice(orders));
		}
		model.addAttribute("labels",labels);
		model.addAttribute("counts",counts);
		model.addAttribute("totalPrices",totalPrices);
		return "orders/view";
	}

	private Double countPrice(List<Order> orders) {
		double sum = 0;
		for(Order order : orders){
			sum+=order.getActuallyPrice();
		}
		return sum;
	}

}
