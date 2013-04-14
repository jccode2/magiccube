/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.base.exception;

/**
 * 模型异常
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public class ModelException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5321852505368443299L;

	/**
     * 异常构造
     * 
     * @param message 异常消息
     * @param throwable 引发的异常类
     */
    public ModelException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
