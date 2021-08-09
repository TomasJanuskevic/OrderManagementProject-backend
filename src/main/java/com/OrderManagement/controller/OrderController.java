package com.OrderManagement.controller;

import com.OrderManagement.model.Order;
import com.OrderManagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value="/order")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
