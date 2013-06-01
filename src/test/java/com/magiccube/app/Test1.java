package com.magiccube.app;

import org.junit.Test;

import com.magiccube.order.util.OrderDiscount;

public class Test1 {

	@Test
	public void testDiscount() {
		OrderDiscount d = new OrderDiscount();
		System.out.println(OrderDiscount.Type.FIRST_ORDER.values());
	}
}
