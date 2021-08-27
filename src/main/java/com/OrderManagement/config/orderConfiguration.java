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
    CommandLineRunner commandLineRunner(BouquetRepository bouquetRepository,
                                        BouquetFlowerRepository bouquetFlowerRepository,
                                        CustomerRepository customerRepository,
                                        FlowerRepository flowerRepository,
                                        OrderRepository orderRepository,
                                        UserRepository userRepository) {

        return args -> {
            User user = new User("Tom", "pass");
            userRepository.save(user);

            Flower rozeRaudona = new Flower("Roze", 0.8, "Raudona", user);
            flowerRepository.save(rozeRaudona);
            Flower rozeBalta = new Flower("Roze", 0.8, "Balta", user);
            flowerRepository.save(rozeBalta);
            Flower rozeRozine = new Flower("Roze", 0.8, "Rozine", user);
            flowerRepository.save(rozeRozine);
            Flower rozeGeltona = new Flower("Roze", 0.8, "Geltona", user);
            flowerRepository.save(rozeGeltona);
            Flower eustomaBalta = new Flower("Eustoma", 1.1, "Balta", user);
            flowerRepository.save(eustomaBalta);
            Flower eustomaVioletine = new Flower("Eustoma", 1.1, "Violetine", user);
            flowerRepository.save(eustomaVioletine);


            Customer customer1 = new Customer("Ramune", "Petrauskene", "ramune@gmail.com", "+37067365862",
                    "www.facebook.com/ramune", "Savanoriu.g 5", user);

            customerRepository.save(customer1);

            Order order1 = new Order(5.0, "Aprasymas", true,
                    LocalDate.of(2021, 8, 2), OrderStatus.GAMINAMAS, customer1);
            orderRepository.save(order1);

            Bouquet bouquet1 = new Bouquet("Zalias", 20.0, 1, 3.0, order1);
            bouquetRepository.save(bouquet1);

            BouquetFlower bouquetFlower1 = new BouquetFlower(5, bouquet1, rozeRaudona);
            bouquetFlowerRepository.save(bouquetFlower1);

        };
    }
}
