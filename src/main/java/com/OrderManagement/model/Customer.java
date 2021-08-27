package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String facebookAcc;
    private String address;

    @JsonBackReference (value = "customers")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference (value = "orders")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String lastname, String email, String phoneNumber,
                    String facebookAcc, String address, User user) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookAcc = facebookAcc;
        this.address = address;
        this.user = user;
    }

    public Customer(String name, String lastname, String email, String phoneNumber, String facebookAcc, String address, List<Order> orders) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookAcc = facebookAcc;
        this.address = address;
        this.orders = orders;
    }
}
