package com.OrderManagement.service;

import com.OrderManagement.model.Order;
import com.OrderManagement.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
        log.info("Order was added successfully");
    }
}
