package com.OrderManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;

    @JsonManagedReference (value = "customers")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    @JsonManagedReference (value = "flowers")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Flower> flowers = new LinkedHashSet<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
