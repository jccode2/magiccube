package com.magiccube.v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.magiccube.v2.dao.OrderDao;
import com.magiccube.v2.entity.Order;

/**
 * @author magic
 */
@Component
@Transactional(readOnly = true)
public class OrderService {

	private OrderDao orderDao;

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public List<Order> findAllByDateString(String dateStr) {
		return (List<Order>) orderDao.findAllByDateString(dateStr);
	}
}
