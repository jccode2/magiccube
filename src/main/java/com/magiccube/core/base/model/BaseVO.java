/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
	本软件由千方百味公司开发
*****************************************************************************/
package com.magiccube.core.base.model;

import org.directwebremoting.annotations.DataTransferObject;

import com.magiccube.core.util.tools.JsonUtil;


/**
 * VO基础类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-15 
 * 
 */
@DataTransferObject
public class BaseVO {
	
	public String toString() {
		return JsonUtil.objectToJson(this) ;
	}
}
