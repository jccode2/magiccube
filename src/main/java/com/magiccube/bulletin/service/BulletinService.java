package com.magiccube.bulletin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magiccube.bulletin.dao.BulletinDAO;
import com.magiccube.bulletin.model.BulletinVO;
import com.magiccube.core.base.service.BaseService;
import com.magiccube.food.service.FoodService;

public class BulletinService extends BaseService {

	final static Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

	private BulletinDAO bulletinDAO;
	
	public BulletinDAO getBulletinDAO() {
		return bulletinDAO;
	}

	public void setBulletinDAO(BulletinDAO bulletinDAO) {
		this.bulletinDAO = bulletinDAO;
	}

	public int insertBulletin(BulletinVO bulletinVO) {
		return bulletinDAO.insertBulletin(bulletinVO);
	}
	
	public int updateBulletin(BulletinVO bulletinVO) {
		return bulletinDAO.updateBulletin(bulletinVO);
	}
	
	public int deleteBulletin(int id) {
		return bulletinDAO.deleteBulletin(id);
	}
	
	public BulletinVO findBulletin(int id) {
		return bulletinDAO.findBulletin(id);
	}
	
	public List<BulletinVO> findAllBulletins() {
		return bulletinDAO.findAllBulletins();
	}
	
	public List<BulletinVO> findAllEnableBulletins() {
		return bulletinDAO.findAllEnableBulletins();
	}
}
