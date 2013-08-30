package com.magiccube.app;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import com.magiccube.order.util.OrderDiscount;

public class Test1 {

	//@Test
	public void testDiscount() {
		OrderDiscount d = new OrderDiscount();
		System.out.println(OrderDiscount.Type.FIRST_ORDER.values());
	}
	
	//@Test
	public void t() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(c.getTime());
		c.add(Calendar.DATE, -1);
		String yesterday = sdf.format(c.getTime());
		c.add(Calendar.DATE, -1);
		String theDayBeforeYesterday = sdf.format(c.getTime());
		
		System.out.println(today);
		System.out.println(yesterday);
		System.out.println(theDayBeforeYesterday);
		
		assertTrue(true);
	}
	
	@Test
	public void t2() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		String s1 = sdf.format(c.getTime());
		
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		String s2 = sdf.format(c.getTime());
		
		System.out.println(s1);
		System.out.println(s2);
		
		assertTrue(true);
	}
}
