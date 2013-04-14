/******************************************************************************
* Copyright (C) 2013 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
******************************************************************************/
package com.magiccube.component.email;

import java.util.List;

/**
 * 
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2013-1-13 黄科林
 */
public interface INotesMessage {
	/**
     * <p>
     * 获取收件人地址（可多人，也可单人）
     * </p>
     * <p>
     * Vector 容器内为收件人地址，类型为String
     * </p>
     * <p>
     * 收件人地址格式为Notes收件人地址格式
     * </p>
     * <p>
     * 例如：Brian Green/Acme@AcmeMAIL、Jane White/Acme
     * </p>
     * 
     * @return 收件人地址
     */
    List<String> getSendTo();
    
    /**
     * <p>
     * 获取抄送人地址（可多人，也可单人）
     * </p>
     * <p>
     * Vector 容器内为抄送人地址，类型为String
     * </p>
     * <p>
     * 抄送人地址格式为Notes收件人地址格式
     * </p>
     * <p>
     * 例如：Brian Green/Acme@AcmeMAIL、Jane White/Acme
     * </p>
     * 
     * @return 抄送人地址
     */
    List<String> getCopyTo();
    
    /**
     * 获取发送邮件主题
     * 
     * @return 发送邮件主题
     */
    String getSubject();
    
    /**
     * <p>
     * 获取发送邮件内容（不带附件）
     * </p>
     * <p>
     * 邮件内容的换行采用"\n"
     * </p>
     * 
     * @return 发送邮件内容
     */
    String getMessage();
    
    /**
     * @return 发送服务器
     */
    String getSendServer();
    
    /**
     * @return 发送方邮箱地址
     */
    String getSendMail();
    
    /**
     * @return 发送邮箱的账号
     */
    String getSendAccount();
    
    /**
     * @return 发送邮箱的密码
     */
    String getSendPassword();
}
