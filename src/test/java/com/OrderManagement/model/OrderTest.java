package com.OrderManagement.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderTest {

    private static Order order;

    @BeforeAll
    static void beforeAll() {
        Flower flower1 = new Flower("Roze", 1.5, "Raudona");
        Flower flower2 = new Flower("Tulpe", 1.4, "Geltona");
        Flower flower3 = new Flower("Roze", 1.2, "Geltona");
        Flower flower4 = new Flower("Tulpe", 1.3, "Raudona");

        List<BouquetFlower> flowersForBouquet1 = Arrays.asList(new BouquetFlower(2, flower1),
                new BouquetFlower(3, flower2));
        List<BouquetFlower> flowersForBouquet2 = Arrays.asList(new BouquetFlower(5, flower3),
                new BouquetFlower(7, flower4));

        List<Bouquet> bouquetList =
                Arrays.asList(new Bouquet("Pirmas", 10, 2, 2.5, flowersForBouquet1),
                        new Bouquet("Antras", 25, 1, 3.4, flowersForBouquet2));
        order = new Order(5, "Gimtadienis", true,
                LocalDate.of(2021, 8, 2), bouquetList);
    }

    @Test
    void getOrderPrimeCost() {
        double expectedPrimeCost = 37.9;
        double actualPrimeCost = order.getPrimeCost();

        assertThat(actualPrimeCost).isEqualTo(expectedPrimeCost);
    }

    @Test
    void getOrderPrice() {
        double expectedOrderPrice = 45.0;
        double actualOrderPrice = order.getOrderPrice();

        assertThat(actualOrderPrice).isEqualTo(expectedOrderPrice);
    }
}
