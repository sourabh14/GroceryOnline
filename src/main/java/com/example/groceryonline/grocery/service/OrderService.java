package com.example.groceryonline.grocery.service;

import com.example.groceryonline.grocery.dto.OrderDto;

public interface OrderService {
    void createOrder(OrderDto request);
}
