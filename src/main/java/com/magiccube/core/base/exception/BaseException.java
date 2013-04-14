/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.base.exception;

/**
 * 异常基类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-15 黄科林
 */
public class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4312572936568395900L;

	/**
     * 异常构造函数
     * 
     * @param message 异常错误消息
     * @param throwable 引发的异常类
     */
    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
