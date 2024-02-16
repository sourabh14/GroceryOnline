package com.example.qpassessment.grocery.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.qpassessment.grocery.dto.OrderDto;
import com.example.qpassessment.grocery.dto.OrderItemDto;
import com.example.qpassessment.grocery.entity.Item;
import com.example.qpassessment.grocery.entity.SaleOrder;
import com.example.qpassessment.grocery.entity.SaleOrderItem;
import com.example.qpassessment.grocery.exception.ApplicationException;
import com.example.qpassessment.grocery.repository.ItemRepository;
import com.example.qpassessment.grocery.repository.OrderRepository;
import com.example.qpassessment.grocery.service.OrderService;
import com.example.qpassessment.security.entity.User;
import com.example.qpassessment.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void createOrder(OrderDto request) {
        log.info("Creating order: {}", request.getCode());

        if (orderRepository.findByCode(request.getCode()).isPresent()) {
            throw new ApplicationException("Order with code " + request.getCode() + " already exists");
        }

        List<SaleOrderItem> orderItems = prepareOrderItems(request);
        if (request.getOrderItems().size() != orderItems.size()) {
            throw new ApplicationException("Some order items are unfulfillable");
        }

        User user = getCurrentUser();
        SaleOrder order = SaleOrder.builder().code(request.getCode()).orderItems(orderItems).user(user).build();

        orderRepository.save(order);
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElse(null);
    }

    private List<SaleOrderItem> prepareOrderItems(OrderDto request) {
        List<SaleOrderItem> orderItems = new ArrayList<>();

        List<String> itemCodes = request.getOrderItems().stream()
                .map(OrderItemDto::getItemCode).toList();

        Map<String, Item> codeToItem = itemRepository.findAllByCodeIn(itemCodes).stream()
                .collect(Collectors.toMap(Item::getCode, item -> item));

        for (OrderItemDto orderItemDto : request.getOrderItems()) {
            Item item = codeToItem.get(orderItemDto.getItemCode());
            if (item.getInventory().getQuantity() > orderItemDto.getQuantity()) {
                log.info("Adding item: {} with quantity: {} to sale order", orderItemDto.getItemCode(), orderItemDto.getQuantity());
                item.getInventory().setQuantity(item.getInventory().getQuantity() - orderItemDto.getQuantity());
                orderItems.add(SaleOrderItem.builder()
                        .item(item)
                        .quantity(orderItemDto.getQuantity())
                        .build());
            }
            else {
                log.warn("Order item {} is unfulfillable", orderItemDto.getItemCode());
            }
        }

        return orderItems;
    }

}
