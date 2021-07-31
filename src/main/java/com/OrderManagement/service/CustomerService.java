package com.OrderManagement.service;

import com.OrderManagement.exception.CustomerNotFoundException;
import com.OrderManagement.model.Customer;
import com.OrderManagement.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer by id: " + id + " was not found"));
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
        log.info("Customer was added successfully");
    }

    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        if (customerRepository.existsById(customer.getCustomerId())) {
            customerRepository.save(customer);
            log.info("Customer was updated successfully");
        } else {
            throw new CustomerNotFoundException("Customer by id: " + customer.getCustomerId() + " was not found");
        }
    }

    public void deleteCustomerById(Long id) throws CustomerNotFoundException {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            log.info("Customer was deleted successfully");
        } else {
            throw new CustomerNotFoundException("Customer by id: " + id + " was not found");
        }
    }
}
