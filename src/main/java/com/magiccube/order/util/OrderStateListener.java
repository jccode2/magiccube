package com.magiccube.order.util;

import com.magiccube.order.model.OrderVO;

/**
 * 订单状态监听者
 * @author jcchen
 *
 */
public interface OrderStateListener {

	public void orderSubmited(OrderVO orderVO);
}
