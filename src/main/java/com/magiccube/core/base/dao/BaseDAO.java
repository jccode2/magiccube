/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.base.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.magiccube.core.base.exception.DAOException;

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
    protected SqlSessionTemplate sqlSessionTemplate;
    
    /**
     * @param sqlSessionTemplate MyBatis的SqlSessionTemplate模板对象
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    
    /**
     * 根据参数对象新增数据
     * 
     * @param statementId MyBatis的statement的id
     * @param insertObject 待新增参数对象
     */
    public void insert(String statementId, Object insertObject) {
        try {
            sqlSessionTemplate.insert(statementId, insertObject);
        } catch (Exception e) {
            throw new DAOException("根据参数对象新增数据失败,参数列表(statementId:" + statementId + " insertObject:" + insertObject + ")", e);
        }
    }
}
