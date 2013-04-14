/******************************************************************************
* Copyright (C) 2012 ShenZhen 1000funs Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳千方百味公司开发研制。
*****************************************************************************/
package com.magiccube.core.util.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.core.base.exception.ModelException;
import com.magiccube.core.base.model.BaseVO;

/**
 * class工具类
 *
 * @author 黄科林
 * @since jdk6.0
 * @version  2012-12-16 黄科林
 */
public final class ClassUtil {

    /**
     * 构造函数
     */
    private ClassUtil() {
    }
    
    /**
     * 日志
     */
    protected final static Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
    
    /**
     * 级联取得class中的属性
     * 
     * @param byClass Class
     * @return 字段列表
     */
    public static List<Field> getFieldByClass(Class<?> byClass) {
        return getFieldByClass(byClass, null);
    }
    
    /**
     * 级联取得class中的属性
     * 
     * @param byClass 待操作的Class
     * @param classFieldFilter 过滤字段
     * @return 字段列表
     */
    public static List<Field> getFieldByClass(Class<?> byClass, ClassFieldFilter classFieldFilter) {
        List<Field> lstField = new ArrayList<Field>(10);
        if (byClass.getSuperclass() != Object.class) {
            lstField.addAll(getFieldByClass(byClass.getSuperclass()));
        }
        Field[] objFields = byClass.getDeclaredFields();
        for (Field objField : objFields) {
            if (classFieldFilter != null) {
                if (classFieldFilter.isFilter(byClass, objField)) {
                    lstField.add(objField);
                }
            } else {
                lstField.add(objField);
            }
        }
        return lstField;
    }
    
    /**
     * 级联取得class中的属性
     * 
     * @param byClass 待操作的Class
     * @param fieldName c
     * @return 字段
     */
    public static Field getFieldByName(Class<?> byClass, String fieldName) {
        Field objResultField = null;
        try {
            objResultField = byClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            LOGGER.debug(byClass + "类中找不到字段" + fieldName);
        }
        if (objResultField == null) {
            if (byClass.getSuperclass() != Object.class) {
                objResultField = getFieldByName(byClass.getSuperclass(), fieldName);
            }
        }
        return objResultField;
    }
    
    /**
     * 取得VO的Table注解的name属性值，默认为VO类名
     * 
     * @param t 实体对象
     * @param <T> BaseVO实体对象类型
     * @return Table表名
     */
    public static <T extends BaseVO> String getTableName(T t) {
        return getTableName(t.getClass());
    }
    
    /**
     * 取得VO的Table注解的name属性值，默认为VO类名
     * 
     * @param clazz 实体对象class
     * @param <T> BaseVO实体对象类型
     * @return Table表名
     */
    public static <T extends BaseVO> String getTableName(Class<T> clazz) {
        String objResult = null;
        if (clazz.isAnnotationPresent(Table.class)) {
            Table objTableAnnotation = clazz.getAnnotation(Table.class);
            objResult = objTableAnnotation.name();
            if (StringUtil.isEmpty(objResult)) {
                objResult = clazz.getSimpleName();
            }
        }
        return objResult;
    }
    
    /**
     * 取得VO的Entity注解的name属性值，默认为VO类名
     * 
     * @param t 实体对象
     * @param <T> BaseVO实体对象类型
     * @return Entity实体名称
     */
    public static <T extends BaseVO> String getEntityName(T t) {
        return getEntityName(t.getClass());
    }
    
    /**
     * 取得VO的Entity注解的name属性值，默认为VO类名
     * 
     * @param clazz 实体对象class
     * @param <T> BaseVO实体对象类型
     * @return Entity实体名称
     */
    public static <T extends BaseVO> String getEntityName(Class<T> clazz) {
        String objResult = null;
        if (clazz.isAnnotationPresent(Entity.class)) {
            Entity objEntityAnnotation = clazz.getAnnotation(Entity.class);
            objResult = objEntityAnnotation.name();
            if (StringUtil.isEmpty(objResult)) {
                objResult = clazz.getSimpleName();
            }
        }
        return objResult;
    }
    
    /**
     * 取得VO主键名称
     * 
     * @param t 实体对象
     * @param <T> BaseVO实体对象类型
     * @return 主键
     */
    public static <T extends BaseVO> String getPrimaryKeyName(T t) {
        return getPrimaryKeyName(t.getClass());
    }
    
    /**
     * 取得VO主键名称
     * 
     * @param clazz 实体对象class
     * @param <T> BaseVO实体对象类型
     * @return 主键
     */
    public static <T extends BaseVO> String getPrimaryKeyName(Class<T> clazz) {
        Object objResult = null;
        Field[] objFields = clazz.getDeclaredFields();
        for (Field objField : objFields) {
            if (objField.isAnnotationPresent(Id.class)) {
                try {
                    objField.setAccessible(true);
                    objResult = objField.getName();
                } catch (SecurityException e) {
                    throw new ModelException("反射获取主键属性名称" + objField.getName() + "时出错", e);
                }
            }
        }
        return objResult != null ? objResult.toString() : StringUtil.EMPTY;
    }
    
    /**
     * 取得VO主键的值
     * 
     * @param t 实体对象
     * @param <T> BaseVO实体对象类型
     * @return 主键
     */
    public static <T extends BaseVO> String getPrimaryKeyValue(T t) {
        Object objResult = null;
        Field[] objFields = t.getClass().getDeclaredFields();
        for (Field objField : objFields) {
            if (objField.isAnnotationPresent(Id.class)) {
                try {
                    objField.setAccessible(true);
                    objResult = objField.get(t);
                } catch (IllegalAccessException e) {
                    throw new ModelException("反射获取主键属性" + objField.getName() + "值时出错", e);
                }
            }
        }
        return objResult != null ? objResult.toString() : StringUtil.EMPTY;
    }
}
