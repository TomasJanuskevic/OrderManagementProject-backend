package com.OrderManagement.controller;

import com.OrderManagement.exception.OrderNotFoundException;
import com.OrderManagement.model.Customer;
import com.OrderManagement.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/email/{orderId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> sendEmail(@RequestBody Customer customer, @PathVariable Long orderId) throws MessagingException, OrderNotFoundException {
        emailService.sendEmail(customer, orderId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
