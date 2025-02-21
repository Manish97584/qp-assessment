package com.qp.service;

import java.util.List;

import com.qp.dto.OrderItemRequest;
import com.qp.dto.OrderRequestDto;
import com.qp.entity.OrderEntity;

public interface OrderService {
	OrderEntity placeOrder(OrderRequestDto orderRequest);
    List<OrderEntity> getUserOrders(Long userId);
}
