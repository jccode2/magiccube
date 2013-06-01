package com.magiccube.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.magiccube.order.util.OrderDiscount;

public class OrderDiscountTest {

	@Test
	public void testOrderDiscount() {
		OrderDiscount od = new OrderDiscount();
		assertTrue(od.getState() == 0);
	}

	@Test
	public void testOrderDiscountInt() {
		OrderDiscount od = new OrderDiscount(7);
		assertTrue(od.getState() == 7);
	}

	@Test
	public void testAddDiscount() {
		OrderDiscount od = new OrderDiscount();
		od.addDiscount(OrderDiscount.Type.FIRST_ORDER);
		assertTrue(od.getState() == 1);
	}

	@Test
	public void testRemoveDiscount() {
		OrderDiscount od = new OrderDiscount(2);
		od.removeDiscount(OrderDiscount.Type.GT5_PIECE);
		assertTrue(od.getState() == 0);
	}

	@Test
	public void testIsDiscountType() {
		OrderDiscount od = new OrderDiscount(2);
		assertFalse(od.isDiscount(OrderDiscount.Type.FIRST_ORDER));
		assertTrue(od.isDiscount(OrderDiscount.Type.GT5_PIECE));
		
		od = new OrderDiscount(7);
		assertTrue(od.isDiscount(OrderDiscount.Type.FIRST_ORDER));
		assertTrue(od.isDiscount(OrderDiscount.Type.GT5_PIECE));
		assertTrue(od.isDiscount(OrderDiscount.Type.EARLIER_THEN_10));
	}

	@Test
	public void testIsDiscount() {
		OrderDiscount od = new OrderDiscount(4);
		assertTrue(od.isDiscount());
		
		od = new OrderDiscount();
		assertFalse(od.isDiscount());
	}

	@Test
	public void testGetDiscounts() {
		OrderDiscount od = new OrderDiscount(7);
		assertTrue(od.getDiscounts().size() == 3);
	}

	@Test
	public void testGetDiscountPrice() {
		OrderDiscount od = new OrderDiscount(5);
		assertTrue(od.getDiscountPrice() == 1.0);
	}

	@Test
	public void testGetSetState() {
		OrderDiscount od = new OrderDiscount();
		assertTrue(od.getState() == 0);
		
		od.setState(7);
		assertTrue(od.getState() == 7);
	}
	
	@Test
	public void testIntegration() {
		OrderDiscount od = new OrderDiscount(5);
		assertTrue(od.isDiscount());
		assertTrue(od.isDiscount(OrderDiscount.Type.FIRST_ORDER));
		assertTrue(od.isDiscount(OrderDiscount.Type.EARLIER_THEN_10));
		assertTrue(od.getDiscountPrice() == 1);
		
		for(OrderDiscount.Type t : od.getDiscounts()) {
			System.out.println(t.getShowText());
		}
		
		od.removeDiscount(OrderDiscount.Type.FIRST_ORDER);
		assertFalse(od.isDiscount(OrderDiscount.Type.FIRST_ORDER));
		assertTrue(od.getDiscountPrice() == 0);
	}

}
