package com.magiccube.bulletin.dao;

import java.util.List;

import com.magiccube.bulletin.model.BulletinVO;
import com.magiccube.core.base.dao.BaseDAO;

public class BulletinDAO extends BaseDAO {

	public int insertBulletin(BulletinVO bulletinVO) {
		this.sqlSessionTemplate.insert("com.magiccube.bulletin.insertBulletin", bulletinVO);
		return bulletinVO.getId();
	}
	
	public int updateBulletin(BulletinVO bulletinVO) {
		return this.sqlSessionTemplate.update("com.magiccube.bulletin.updateBulletin", bulletinVO);
	}
	
	public int deleteBulletin(int id) {
		return this.sqlSessionTemplate.delete("com.magiccube.bulletin.deleteBulletin", id);
	}
	
	public BulletinVO findBulletin(int id) {
		return (BulletinVO)this.sqlSessionTemplate.selectOne("com.magiccube.bulletin.findBulletin", id);
	}
	
	public List<BulletinVO> findAllBulletins() {
		List<BulletinVO> ret = this.sqlSessionTemplate.selectList("com.magiccube.bulletin.findAllBulletins");
		return ret;
	}
	
	public List<BulletinVO> findAllEnableBulletins() {
		List<BulletinVO> ret = this.sqlSessionTemplate.selectList("com.magiccube.bulletin.findAllEnableBulletins");
		return ret;
	}
}
