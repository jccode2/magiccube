package com.magiccube.order.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.magiccube.order.model.OrderVO;

/**
 * 订单状态发布者
 * @author jcchen
 *
 */
public class OrderStatePublisher {

	/**
	 * 监听者列表
	 */
	@Autowired
	private List<OrderStateListener> listeners = new ArrayList<OrderStateListener>();
	
	public void addListener(OrderStateListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(OrderStateListener listener) {
		listeners.remove(listener);
	}
	
	public void notify(OrderVO orderVO) {
		for(OrderStateListener listener : listeners) {
			listener.orderSubmited(orderVO);
		}
	}

	public List<OrderStateListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<OrderStateListener> listeners) {
		this.listeners = listeners;
	}
	
}
