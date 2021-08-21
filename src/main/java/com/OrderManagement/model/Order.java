package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Order_Tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private double deliveryPrice;
    private String description;
    private boolean requiredDelivery;
    private LocalDate orderDate;
    private double primeCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.GAMINAMAS;

    @JsonBackReference(value = "orders")
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonManagedReference(value = "bouquet")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Bouquet> bouquets = new ArrayList<>();

    public Order(double deliveryPrice, String description, boolean requiredDelivery, LocalDate orderDate, Customer customer) {
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.requiredDelivery = requiredDelivery;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public double orderPrimeCost() {
        return bouquets.stream().mapToDouble(Bouquet::getPrimeCost).sum();
    }
}
