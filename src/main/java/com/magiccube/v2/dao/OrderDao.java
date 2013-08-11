package com.magiccube.v2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.magiccube.v2.entity.Order;

public interface OrderDao extends PagingAndSortingRepository<Order, Long>, JpaSpecificationExecutor<Order> {

	@Query("select Order from Order order where order.createTime like ?1)")
	List<Order> findAllByDateString(String dateStr);
}
