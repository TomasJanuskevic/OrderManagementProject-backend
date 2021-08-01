package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bouquetId;
    private double bouquetPrice;
    private int quantity;

    @JsonManagedReference
    @OneToOne(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private Accessories accessories;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @JsonManagedReference
    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private Set<BouquetFlower> bouquetFlower = new HashSet<>();

    public Bouquet(double bouquetPrice, int quantity, Order order) {
        this.bouquetPrice = bouquetPrice;
        this.quantity = quantity;
        this.order = order;
    }
}
