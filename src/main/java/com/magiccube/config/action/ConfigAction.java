package com.magiccube.config.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magiccube.config.model.ConfigVO;
import com.magiccube.config.service.ConfigService;

@Controller
public class ConfigAction {

	@Autowired
	private ConfigService configService;
	
	/**
	 * 插入配置信息
	 * @param configVO
	 * @return
	 */
	public int insertConfig(ConfigVO configVO) {
		return configService.insertConfig(configVO);
	}
	
	public int insertConfig(String key, String value) {
		return insertConfig(new ConfigVO(key, value));
	}
	
	/**
	 * 更新配置信息
	 * @param configVO
	 * @return
	 */
	public int updateConfig(ConfigVO configVO) {
		return configService.updateConfig(configVO);
	}
	
	public int updateConfig(String key, String value) {
		return updateConfig(new ConfigVO(key, value));
	}
	
	/**
	 * 获取一个配置值
	 * @param key
	 * @return
	 */
	public ConfigVO getConfig(String key) {
		return configService.getConfig(key);
	}
	
	/**
	 * 获取一个配置值
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key) {
		return getConfig(key).getValue();
	}
	
	/**
	 * 获取一个配置值,如果不存在,则使用defaultValue新增一个并返回
	 * @param key
	 * @param defaultValue
	 * @param className
	 * @return
	 */
	public ConfigVO getConfig(String key, String defaultValue, String className) {
		return configService.getConfig(key, defaultValue, className);
	}
	
	@RequestMapping("/config/test")
	public @ResponseBody ConfigVO test(@RequestParam String key) {
		ConfigVO vo = getConfig(key);
		System.out.println(vo);
		return vo;
	}
}
