package com.example.groceryonline.grocery.controller;

import com.example.groceryonline.grocery.dto.OrderDto;
import com.example.groceryonline.grocery.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto request)
            throws BadRequestException {
        orderService.createOrder(request);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

}
