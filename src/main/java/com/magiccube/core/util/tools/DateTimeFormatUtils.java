/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 提供日期或者是时间格式化处理工具类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public final class DateTimeFormatUtils {
	
	/** 带年月日时分秒的日期时间格式字符串: yyyy-MM-dd HH:mm:ss */
    public final static String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /** 带年月日时分的日期时间格式字符串: yyyy-MM-dd HH:mm */
    public final static String ISO_DATETIME_FORMAT_SHORT = "yyyy-MM-dd HH:mm";
    
    /** 带年月日的日期格式字符串: yyyy-MM-dd */
    public final static String ISO_DATE_FORMAT = "yyyy-MM-dd";
    
    /** 带年月的日期格式字符串: yyyy-MM */
    public final static String ISO_SHORT_DATE_FORMAT = "yyyy-MM";
    
    /** 带时分秒的时间格式字符串: HH:mm:ss */
    public final static String ISO_TIME_FORMAT = "HH:mm:ss";
    
    /**
     * 构造函数
     */
    private DateTimeFormatUtils() {
    }
    
    /**
     * 根据日期时间格式yyyy-MM-dd HH:mm:ss来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @return 返回的是格式化后的字符串
     */
    public static String formatDateTime(Date value) {
        return formatDateTime(value, ISO_DATETIME_FORMAT);
    }
    
    /**
     * 根据日期时间格式yyyy-MM-dd HH:mm来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @return 返回的是格式化后的字符串
     */
    public static String formatShortDateTime(Date value) {
        return formatDateTime(value, ISO_DATETIME_FORMAT_SHORT);
    }
    
    /**
     * 根据日期格式yyyy-MM-dd来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @return 返回的是格式化后的字符串
     */
    public static String formatDate(Date value) {
        return formatDateTime(value, ISO_DATE_FORMAT);
    }
    
    /**
     * 根据日期格式yyyy-MM来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @return 返回的是格式化后的字符串
     */
    public static String formatShortDate(Date value) {
        return formatDateTime(value, ISO_SHORT_DATE_FORMAT);
    }
    
    /**
     * 根据时间格式HH:mm:ss来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @return 返回的是格式化后的字符串
     */
    public static String formatTime(Date value) {
        return formatDateTime(value, ISO_TIME_FORMAT);
    }
    
    /**
     * 根据指定的日期时间格式来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @param defaultFormat 指定的日期时间格式,为空时将使用默认的日期时间格式yyyy-MM-dd HH:mm:ss
     * @return 返回的是格式化后的字符串
     */
    public static String formatDateTime(Date value, String defaultFormat) {
        if (value == null) {
            return "";
        }
        String strFormatStyle = StringUtil.isEmpty(defaultFormat) ? ISO_DATETIME_FORMAT : defaultFormat;
        SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(strFormatStyle);
        return objSimpleDateFormat.format(value);
    }
    
    /**
     * 根据指定的日期时间格式来获取指定的对象的字符串信息
     * 
     * @param value 需要被转换的日期时间对象引用
     * @param format 指定的日期时间格式,为空时将使用默认的日期时间格式yyyy-MM-dd HH:mm:ss
     * @return 返回的是格式化后的字符串
     */
    public static String getCurrentFormatDateTime(Date value, String format) {
        return formatDateTime(new Date(), format);
    }
    
    /**
     * 获取指定日期所在的季度。
     * 
     * <pre>
     * DateTimeFormatUtils.getQuarter(java.sql.Date.valueOf(&quot;2004-01-01&quot;)=1
     * DateTimeFormatUtils.getQuarter(java.sql.Date.valueOf(&quot;2004-05-01&quot;)=2
     * DateTimeFormatUtils.getQuarter(java.sql.Date.valueOf(&quot;2004-09-01&quot;)=3
     * DateTimeFormatUtils.getQuarter(java.sql.Date.valueOf(&quot;2004-12-01&quot;)=4
     * DateTimeFormatUtils.getQuarter(null) = 0;
     * </pre>
     * 
     * @param date 需要判断的时间，类型为java.util.Date.
     * @return 返回指定日期所在的季度。
     */
    public static int getQuarter(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.setTime(date);
        int iMonth = objCalendar.get(Calendar.MONTH) + 1;
        if ((iMonth >= 1) && (iMonth <= 3)) {
            return 1;
        } else if ((iMonth >= 4) && (iMonth <= 6)) {
            return 2;
        } else if ((iMonth >= 7) && (iMonth <= 9)) {
            return 3;
        } else {
            return 4;
        }
    }
    
    /**
     * 得到指定日期所在的周。
     * 
     * @param dateValue 需要判断的日期类型。(java.util.Date)
     * @return 得到日期所在的周。
     */
    public static String getWeekID(Date dateValue) {
        String strWeekID = "";
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.setTime(dateValue);
        int iYear = objCalendar.get(Calendar.YEAR);
        int iWeek = objCalendar.get(Calendar.WEEK_OF_YEAR);
        if (iWeek >= 10) {
            strWeekID = iYear + String.valueOf(iWeek);
        } else {
            strWeekID = iYear + "0" + iWeek;
        }
        return strWeekID;
    }
    
    /**
     * 得到所在周的第一天
     * 
     * @param year 需要判断的年份。
     * @param week 需要判断的第几周。
     * @return 得到指定年份，指定周的第一天。
     */
    public static String firstDayOfWeek(int year, int week) {
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(Calendar.YEAR, year);
        objCalendar.set(Calendar.WEEK_OF_YEAR, week);
        // 考虑Calendar.setFirstDayOfWeek()
        objCalendar.add(Calendar.DAY_OF_WEEK, (-1 * objCalendar.get(Calendar.DAY_OF_WEEK)) + 2);
        return formatDate(objCalendar.getTime());
    }
    
    /**
     * 得到所在周的最后一天
     * 
     * @param year 需要判断的年份。
     * @param week 需要判断的第几周。
     * @return 得到指定年份，指定周的最后一天。
     */
    public static String endDayOfWeek(int year, int week) {
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(Calendar.YEAR, year);
        objCalendar.set(Calendar.WEEK_OF_YEAR, week);
        // 考虑Calendar.setFirstDayOfWeek()
        objCalendar.add(Calendar.DAY_OF_WEEK, (-1 * objCalendar.get(Calendar.DAY_OF_WEEK)) + 2 + 6);
        return formatDate(objCalendar.getTime());
    }
    
    /**
     * 得到指定日期所在周的前一周或是下一周
     * 
     * @param year 需要判断的年份。
     * @param week 需要判断的第几周。
     * @param direction true 获取前一周。<BR>
     *            false 获取后一周<BR>
     * @return 得到指定日期所在周的前一周或是下一周
     */
    public static String rollWeek(int year, int week, boolean direction) {
        String strWeekID = "";
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(Calendar.YEAR, year);
        objCalendar.set(Calendar.WEEK_OF_YEAR, week);
        if (direction) { //
            objCalendar.add(Calendar.DAY_OF_WEEK, 7);
        } else {
            objCalendar.add(Calendar.DAY_OF_WEEK, -7);
        }
        int iYear = objCalendar.get(Calendar.YEAR);
        int iWeek = objCalendar.get(Calendar.WEEK_OF_YEAR);
        if (iWeek < 10) {
            strWeekID = iYear + "0" + iWeek;
        } else {
            strWeekID = iYear + String.valueOf(iWeek);
        }
        return strWeekID;
    }
    
    /**
     * 返回当前周除指定日期以外的日期集合.
     * 
     * @param year 需要判断的年份。
     * @param week 需要判断的第几周。
     * @param deviateDate 需要被排除的日期字符串集合
     * @return 返回当前周除指定日期以外的日期集合。
     */
    public static String[] otherDayOfWeek(int year, int week, String[] deviateDate) {
        List<String> objDates = new Vector<String>();
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(Calendar.YEAR, year);
        objCalendar.set(Calendar.WEEK_OF_YEAR, week);
        for (int i = 0; i < 7; i++) {
            objCalendar.add(Calendar.DAY_OF_WEEK, (-1 * objCalendar.get(Calendar.DAY_OF_WEEK)) + 2 + i);
            objDates.add(formatDate(objCalendar.getTime()));
        }
        if (deviateDate != null) {
            for (int j = 0; j < deviateDate.length; j++) {
                if (objDates.contains(deviateDate[j])) {
                    objDates.remove(deviateDate[j]);
                }
            }
        }
        String[] strRtn = new String[objDates.size()];
        for (int k = 0; k < objDates.size(); k++) {
            strRtn[k] = objDates.get(k);
        }
        return strRtn;
    }
}
