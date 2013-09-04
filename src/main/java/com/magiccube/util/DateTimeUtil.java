/******************************************************************************
 * Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
 * All Rights Reserved.
 *****************************************************************************/
package com.magiccube.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 日期时间转换工具类
 * 
 * @since 2013-9-1
 * @author ksfifa
 */
public class DateTimeUtil {
	
	/**
	 * 私有構造函數
	 */
	private DateTimeUtil() {
		
	}

	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) {
		if(date==null) {
			return null;
		}
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
		
		if(str==null){
			return null;
		}
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
}
