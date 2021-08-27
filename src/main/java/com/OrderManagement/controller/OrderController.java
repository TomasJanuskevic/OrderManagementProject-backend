package com.OrderManagement.controller;

import com.OrderManagement.exception.OrderNotFoundException;
import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.Order;
import com.OrderManagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/order")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Order>> getSortedOrdersByUserId(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(orderService.getSortedOrdersByUserId(userId), HttpStatus.OK);
    }

}
