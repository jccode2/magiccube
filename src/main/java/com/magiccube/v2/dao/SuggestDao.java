package com.magiccube.v2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.magiccube.v2.entity.Suggest;

public interface SuggestDao extends PagingAndSortingRepository<Suggest, Long>, JpaSpecificationExecutor<Suggest> {
	
}
