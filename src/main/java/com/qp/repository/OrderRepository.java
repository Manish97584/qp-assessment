package com.qp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	List<OrderEntity> findByUserId(Long userId);
}
