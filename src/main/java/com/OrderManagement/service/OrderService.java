package com.OrderManagement.service;

import com.OrderManagement.exception.FlowerNotFoundException;
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

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
        log.info("Order was added successfully");
    }

    public void updateOrder(Order order) {
            orderRepository.save(order);
            log.info("Order was updated successfully");
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        log.info("Order was deleted successfully");
    }
}
