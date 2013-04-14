/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import java.util.List;

/**
 * 邮件包装器接口
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public interface IMailWrapper {
	/**
     * 获得收件人地址集合
     * 
     * @return 收件人地址集合(List<String>，String 格式：xxx@xxx)
     */
    List<String> getSendTo();
    
    /**
     * 获得抄送人地址集合
     * 
     * @return 抄送人地址集合(List<String>，String 格式：xxx@xxx)
     */
    List<String> getCopyTo();
    
    /**
     * 获得邮件主题
     * 
     * @return 获得邮件主题。主题不能为空
     */
    String getSubject();
    
    /**
     * 获得邮件内容
     * 
     * @return 邮件内容
     */
    String getContent();
}
