package com.OrderManagement.controller;

import com.OrderManagement.exception.CustomerNotFoundException;
import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.Customer;
import com.OrderManagement.model.User;
import com.OrderManagement.service.CustomerService;
import com.OrderManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Customer>> findCustomers() {
        return new ResponseEntity<>(customerService.findCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/customer")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customer")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
