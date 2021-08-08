package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference (value = "accessories")
    @OneToOne(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private Accessories accessories;

    @JsonBackReference (value = "bouquet")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @JsonManagedReference (value = "bouquetFlower")
    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private List<BouquetFlower> bouquetFlower = new ArrayList<>();

    public Bouquet(double bouquetPrice, int quantity, Order order) {
        this.bouquetPrice = bouquetPrice;
        this.quantity = quantity;
        this.order = order;
    }
}
