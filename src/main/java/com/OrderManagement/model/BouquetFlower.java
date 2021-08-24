package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class BouquetFlower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bouquetFlowerId;
    private int quantity;

    @JsonBackReference(value = "bouquetFlower")
    @ManyToOne
    @JoinColumn(name = "bouquet_id", nullable = false)
    private Bouquet bouquet;

    @JsonManagedReference(value = "bouquetFlowers")
    @OneToOne
    @JoinColumn(name = "flower_id", nullable = false)
    private Flower flower;

    public BouquetFlower(int quantity, Bouquet bouquet, Flower flower) {
        this.quantity = quantity;
        this.bouquet = bouquet;
        this.flower = flower;
    }

    public BouquetFlower(int quantity, Flower flower) {
        this.quantity = quantity;
        this.flower = flower;
    }

    public double bouquetFlowerPrimeCost() {
        return flower.getPrice() * quantity;
    }
}
