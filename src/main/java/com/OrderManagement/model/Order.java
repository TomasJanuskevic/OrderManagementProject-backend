package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="Order_Tbl")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;
    private double deliveryPrice;
    private String description;
    private boolean requiredDelivery;
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.GAMINAMAS;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Bouquet> bouquets = new HashSet<>();

    public Order(double deliveryPrice, String description, boolean requiredDelivery, LocalDate orderDate, Customer customer) {
        this.deliveryPrice = deliveryPrice;
        this.description = description;
        this.requiredDelivery = requiredDelivery;
        this.orderDate = orderDate;
        this.customer = customer;
    }
}
