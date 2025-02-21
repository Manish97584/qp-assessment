package com.qp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qp.dto.OrderRequestDto;
import com.qp.entity.OrderEntity;
import com.qp.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderEntity>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}
