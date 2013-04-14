package com.magiccube.config.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.config.dao.ConfigDAO;
import com.magiccube.config.model.ConfigVO;
import com.magiccube.food.service.FoodService;

public class ConfigService {

	final static Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

	private ConfigDAO configDAO;
	
	public ConfigDAO getConfigDAO() {
		return configDAO;
	}

	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}

	/**
	 * 插入配置信息
	 * @param configVO
	 * @return
	 */
	public int insertConfig(ConfigVO configVO) {
		return configDAO.insertConfig(configVO);
	}
	
	/**
	 * 更新配置信息
	 * @param configVO
	 * @return
	 */
	public int updateConfig(ConfigVO configVO) {
		return configDAO.updateConfig(configVO);
	}
	
	/**
	 * 获取一个配置值
	 * @param key
	 * @return
	 */
	public ConfigVO getConfig(String key) {
		return configDAO.getConfig(key);
	}
	
	/**
	 * 获取一个配置值,如果不存在,则使用defaultValue新增一个并返回
	 * @param key
	 * @param defaultValue
	 * @param className
	 * @return
	 */
	public ConfigVO getConfig(String key, String defaultValue, String className) {
		ConfigVO configVO = getConfig(key);
		if(configVO != null) return configVO;
		configVO = new ConfigVO(key, defaultValue, className);
		insertConfig(configVO);
		return configVO;
	}
}
