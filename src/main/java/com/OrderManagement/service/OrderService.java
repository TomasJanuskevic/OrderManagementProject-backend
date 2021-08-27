package com.OrderManagement.service;

import com.OrderManagement.exception.OrderNotFoundException;
import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.Order;
import com.OrderManagement.model.User;
import com.OrderManagement.repository.OrderRepository;
import com.OrderManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order getOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order by id: " + id + " was not found"));
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

    public List<Order> getSortedOrdersByUserId(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User by id: " + userId + " was not found"));

        return user.getCustomers().stream()
                .flatMap(customer -> customer.getOrders().stream()).sorted().collect(Collectors.toList());
    }
}
