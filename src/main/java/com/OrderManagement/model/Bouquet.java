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
    private String description;
    private double bouquetPrice;
    private int quantity;
    private double accessoriesPrice;
    @Transient
    private double primeCost;

    @JsonBackReference(value = "bouquet")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @JsonManagedReference(value = "bouquetFlower")
    @OneToMany(mappedBy = "bouquet", cascade = CascadeType.ALL)
    private List<BouquetFlower> bouquetFlowers = new ArrayList<>();

    public Bouquet(String description, double bouquetPrice, int quantity, double accessoriesPrice, Order order) {
        this.description = description;
        this.bouquetPrice = bouquetPrice;
        this.quantity = quantity;
        this.accessoriesPrice = accessoriesPrice;
        this.order = order;
    }

    public Bouquet(String description, double bouquetPrice, int quantity, double accessoriesPrice, List<BouquetFlower> bouquetFlowers) {
        this.description = description;
        this.bouquetPrice = bouquetPrice;
        this.quantity = quantity;
        this.accessoriesPrice = accessoriesPrice;
        this.bouquetFlowers = bouquetFlowers;
    }

    public double getPrimeCost() {
        return bouquetFlowers.stream().mapToDouble(BouquetFlower::bouquetFlowerPrimeCost).sum() + accessoriesPrice;
    }

}
