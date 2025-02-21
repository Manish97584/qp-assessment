package com.qp.dto;


import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {
     Long userId;
     List<OrderItemRequest> items;
}

