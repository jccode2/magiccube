package com.magiccube.v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.magiccube.v2.dao.SuggestDao;
import com.magiccube.v2.entity.Suggest;

/**
 * @author magic
 */
@Component
@Transactional(readOnly = true)
public class SuggestService {

	private SuggestDao suggestDao;

	@Autowired
	public void setSuggestDao(SuggestDao suggestDao) {
		this.suggestDao = suggestDao;
	}

	public List<Suggest> getAllSuggest() {
		return (List<Suggest>) suggestDao.findAll();
	}

}
