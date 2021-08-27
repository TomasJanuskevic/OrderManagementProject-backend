package com.OrderManagement.repository;

import com.OrderManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value="SELECT * FROM customer c INNER JOIN order_tbl o ON o.customer_id = c.customer_id WHERE c.customerId = ", nativeQuery = true)
    Customer findCustomerByOrderId(Long orderId);
}
