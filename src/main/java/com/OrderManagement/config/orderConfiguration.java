package com.OrderManagement.config;

import com.OrderManagement.model.*;
import com.OrderManagement.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class orderConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(AccessoriesRepository accessoriesRepository,
                                        BouquetRepository bouquetRepository,
                                        BouquetFlowerRepository bouquetFlowerRepository,
                                        CustomerRepository customerRepository,
                                        FlowerRepository flowerRepository,
                                        OrderRepository orderRepository,
                                        UserRepository userRepository) {

        return args -> {
            User user1 = new User("Tom", "pass");
            userRepository.save(user1);

            Flower flower1 = new Flower("Roze", 2.20, "Red", user1);
            flowerRepository.save(flower1);

            Customer customer1 = new Customer("Ramune", "Petrauskene", "ramune@gmail.com", "+37067365862",
                    "www.facebook.com/ramune", "Savanoriu.g 5", user1);

            customerRepository.save(customer1);

            Order order1 = new Order(5.0, "Aprasymas", true,
                    LocalDate.of(2021, 8, 2), customer1);
            orderRepository.save(order1);

            Bouquet bouquet1 = new Bouquet(20.0, 1, order1);
            bouquetRepository.save(bouquet1);

            Accessories accessories1 = new Accessories("Aprasymas", 6.5, bouquet1);
            accessoriesRepository.save(accessories1);

            BouquetFlower bouquetFlower1 = new BouquetFlower(5, bouquet1, flower1);
            bouquetFlowerRepository.save(bouquetFlower1);

        };
    }
}
