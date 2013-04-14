/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.lang.reflect.Field;

/**
 * 类字段过滤接口
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public interface ClassFieldFilter {
	
	/**
     * 字段过滤
     * 
     * @param byClass 待操作的Class
     * @param field 字段名
     * @return 是否过滤
     */
    boolean isFilter(Class<?> byClass, Field field);
}
