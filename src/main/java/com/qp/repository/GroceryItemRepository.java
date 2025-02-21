package com.qp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qp.entity.GroceryItemEntity;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItemEntity, Long> {

}
