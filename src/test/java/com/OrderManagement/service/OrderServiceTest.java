package com.OrderManagement.service;

import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.*;
import com.OrderManagement.repository.OrderRepository;
import com.OrderManagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderTestRepository;
    @Mock
    private UserRepository userTestRepository;
    private OrderService orderTestService;

    @BeforeEach
    void setUp() {
        orderTestService = new OrderService(orderTestRepository, userTestRepository);
    }

    @Test
    void getSortedOrdersByUserId() throws UserNotFoundException {
        Flower flower = new Flower("Roze", 1.5, "Raudona");

        List<BouquetFlower> flowersForBouquet1 = Arrays.asList(new BouquetFlower(2, flower));

        List<Bouquet> bouquets = Arrays.asList(new Bouquet("First", 10.0, 1,
                3.5, flowersForBouquet1));

        Order order1 = new Order(5, "First", true,
                LocalDate.of(2021, 8, 2), OrderStatus.TAKEN, bouquets);
        Order order2 = new Order(2, "Second", false,
                LocalDate.of(2021, 5, 10), OrderStatus.IN_PRODUCTION, bouquets);
        Order order3 = new Order(4, "Third", false,
                LocalDate.of(2021, 7, 10), OrderStatus.PRODUCED, bouquets);
        List<Order> orders = Arrays.asList(order1, order2, order3);

        List<Customer> customer = Arrays.asList(new Customer("Bob", "Marley", "bob@gmail.com", "864512356",
                "facebook.com/bob", "Nemuno g. 5", orders));
        Long userId = 1L;
        User user = new User("Mark", "pass", customer);

        given(userTestRepository.findById(userId)).willReturn(Optional.of(user));

        List<Order> expectedSortedList = Arrays.asList(order2, order3, order1);
        List<Order> actualSortedList = orderTestService.getSortedOrdersByUserId(userId);

        assertThat(actualSortedList).isEqualTo(expectedSortedList);
    }
}
