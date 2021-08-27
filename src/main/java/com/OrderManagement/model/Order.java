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


@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "Order_Tbl")
public class Order implements Comparable<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private double deliveryPrice;
    private String description;
    private boolean requiredDelivery;
    private LocalDate orderDate;

    @Transient
    private double orderPrice;
    @Transient
    private double primeCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @JsonBackReference(value = "orders")
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonManagedReference(value = "bouquet")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Bouquet> bouquets = new ArrayList<>();

    public Order(double deliveryPrice, String description, boolean requiredDelivery, LocalDate orderDate,
                 OrderStatus orderStatus, Customer customer) {
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.requiredDelivery = requiredDelivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }

    public Order(double deliveryPrice, String description, boolean requiredDelivery, LocalDate orderDate,
                 OrderStatus orderStatus, List<Bouquet> bouquets) {
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.requiredDelivery = requiredDelivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.bouquets = bouquets;
    }

    public double getPrimeCost() {
        return bouquets.stream().mapToDouble(bouquet -> bouquet.getPrimeCost() * bouquet.getQuantity()).sum();
    }

    public double getOrderPrice() {
        return bouquets.stream().mapToDouble(bouquet -> bouquet.getBouquetPrice() * bouquet.getQuantity()).sum();
    }

    @Override
    public int compareTo(Order o) {
        if (this.getOrderStatus() == OrderStatus.IN_PRODUCTION && o.getOrderStatus() == OrderStatus.PRODUCED) {
            return -1;
        } else if (this.getOrderStatus() == OrderStatus.IN_PRODUCTION && o.getOrderStatus() == OrderStatus.TAKEN) {
            return -1;
        } else if (this.getOrderStatus() == OrderStatus.IN_PRODUCTION && o.getOrderStatus() == OrderStatus.IN_PRODUCTION) {
            return 0;
        } else if (this.getOrderStatus() == OrderStatus.PRODUCED && o.getOrderStatus() == OrderStatus.IN_PRODUCTION) {
            return 1;
        } else if (this.getOrderStatus() == OrderStatus.PRODUCED && o.getOrderStatus() == OrderStatus.TAKEN) {
            return -1;
        } else if (this.getOrderStatus() == OrderStatus.PRODUCED && o.getOrderStatus() == OrderStatus.PRODUCED) {
            return 0;
        } else if (this.getOrderStatus() == OrderStatus.TAKEN && o.getOrderStatus() == OrderStatus.IN_PRODUCTION) {
            return 1;
        } else if (this.getOrderStatus() == OrderStatus.TAKEN && o.getOrderStatus() == OrderStatus.PRODUCED) {
            return 1;
        } else {
            return 0;
        }
    }
}
