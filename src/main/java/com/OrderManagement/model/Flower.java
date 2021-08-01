package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flowerId;
    private String flowerName;
    private double price;
    private String color;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToOne(mappedBy = "flower", cascade = CascadeType.ALL)
    private BouquetFlower bouquetFlower;

    public Flower(String flowerName, double price, String color, User user) {
        this.flowerName = flowerName;
        this.price = price;
        this.color = color;
        this.user = user;
    }
}
