/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.base.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * DAO基类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-15 黄科林
 */
public class BaseDAO {
	
	/**
     * SqlSessionTemplate模板对象 sqlSessionTemplate
     */
    @Resource
    protected SqlSession sqlSessionTemplate;
    
    /**
     * @param sqlSessionTemplate MyBatis的SqlSessionTemplate模板对象
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    
}
