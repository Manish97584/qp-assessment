package com.qp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.dto.OrderItemRequest;
import com.qp.dto.OrderRequestDto;
import com.qp.entity.GroceryItemEntity;
import com.qp.entity.OrderEntity;
import com.qp.entity.OrderItemEntity;
import com.qp.entity.UserEntity;
import com.qp.exception.ResourceNotFoundException;
import com.qp.repository.GroceryItemRepository;
import com.qp.repository.OrderRepository;
import com.qp.repository.UserRepository;
import com.qp.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private GroceryItemRepository groceryRepository;

    @Override
    public OrderEntity placeOrder(OrderRequestDto orderRequest) {
        UserEntity user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        log.info("Placing order for user ID: {}", orderRequest.getUserId());
        List<OrderItemEntity> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
        	log.info("Processing item ID: {} with quantity: {}", itemRequest.getGroceryItemId(), itemRequest.getQuantity());
            GroceryItemEntity groceryItem = groceryRepository.findById(itemRequest.getGroceryItemId())
            		.orElseThrow(() -> new ResourceNotFoundException("Grocery item with ID " + itemRequest.getGroceryItemId() + " not found"));
            
            if (itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be at least 1");
            }
            if (itemRequest.getQuantity() > groceryItem.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for grocery item ID " + groceryItem.getId());
            }

            groceryItem.setQuantity(groceryItem.getQuantity() - itemRequest.getQuantity());
            groceryRepository.save(groceryItem);

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setGroceryItem(groceryItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(groceryItem.getPrice() * itemRequest.getQuantity());

            totalAmount += orderItem.getPrice();
            orderItems.add(orderItem);
        }

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
