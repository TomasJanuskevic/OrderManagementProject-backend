package com.OrderManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Accessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoriesId;
    private String description;
    private double price;

    @OneToOne
    @JoinColumn(name = "bouquet_id", nullable = false)
    private Bouquet bouquet;

    public Accessories(String description, double price, Bouquet bouquet) {
        this.description = description;
        this.price = price;
        this.bouquet = bouquet;
    }
}
