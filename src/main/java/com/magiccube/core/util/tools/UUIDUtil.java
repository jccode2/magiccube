/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public final class UUIDUtil {

    /**
     * 构造函数
     */
    private UUIDUtil() {
    }
    
    /**
     * 生成一个32位的UUID
     * 
     * @return 生成的UUID
     */
    public static String generateUUID() {
        String strUUID = UUID.randomUUID().toString().replaceAll("-", StringUtil.EMPTY);
        return strUUID;
    }
}
