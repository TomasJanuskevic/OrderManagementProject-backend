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

            Flower rozeRaudona = new Flower("Roze", 0.9, "Raudona", user);
            flowerRepository.save(rozeRaudona);
            Flower rozeBalta = new Flower("Roze", 0.8, "Balta", user);
            flowerRepository.save(rozeBalta);
            Flower rozeRozine = new Flower("Roze", 0.8, "Rozine", user);
            flowerRepository.save(rozeRozine);
            Flower rozeGeltona = new Flower("Roze", 0.8, "Geltona", user);
            flowerRepository.save(rozeGeltona);
            Flower rozeVioletine = new Flower("Roze", 0.8, "Violetine", user);
            flowerRepository.save(rozeVioletine);
            Flower rozeKremine = new Flower("Roze", 0.8, "Kremine", user);
            flowerRepository.save(rozeKremine);
            Flower eustomaBalta = new Flower("Eustoma", 1.1, "Balta", user);
            flowerRepository.save(eustomaBalta);
            Flower eustomaVioletine = new Flower("Eustoma", 1.2, "Violetine", user);
            flowerRepository.save(eustomaVioletine);
            Flower eustomaZalia = new Flower("Eustoma", 1.1, "Zalia", user);
            flowerRepository.save(eustomaZalia);
            Flower gvazdikaiBalta = new Flower("Gvazdikai", 0.7, "Balta", user);
            flowerRepository.save(gvazdikaiBalta);
            Flower gvazdikaiZalia = new Flower("Gvazdikai", 0.7, "Zalia", user);
            flowerRepository.save(gvazdikaiZalia);
            Flower gvazdikaiSakelemisBalta = new Flower("Gvazdikai sakelemis", 0.6, "Balta", user);
            flowerRepository.save(gvazdikaiSakelemisBalta);
            Flower gvazdikaiSakelemisRozine = new Flower("Gvazdikai sakelemis", 0.6, "Rozine", user);
            flowerRepository.save(gvazdikaiSakelemisRozine);
            Flower astromerijaBalta = new Flower("Astromerija", 0.9, "Balta", user);
            flowerRepository.save(astromerijaBalta);
            Flower orchidejaBalta = new Flower("Orchideja", 2.0, "Balta", user);
            flowerRepository.save(orchidejaBalta);
            Flower zaluma = new Flower("Zaluma", 0.2, "Zalia", user);
            flowerRepository.save(zaluma);

            Customer ramunePetrauskene = new Customer("Ramune", "Petrauskene", "ramune@gmail.com", "+37067365862",
                    "www.facebook.com/ramune", "Savanoriu.g 5", user);
            customerRepository.save(ramunePetrauskene);
            Customer justinaJonaite = new Customer("Justina", "Jonaite", "justina@gmail.com", "+37067951235",
                    "www.facebook.com/justina", "Traku.g 7", user);
            customerRepository.save(justinaJonaite);

            Order ramuneOrder1 = new Order(5.0, "Gimtadienis", true,
                    LocalDate.of(2021, 9, 10), OrderStatus.PRODUCED, ramunePetrauskene);
            orderRepository.save(ramuneOrder1);
            Order ramuneOrder2 = new Order(0, "Svente", false,
                    LocalDate.of(2021, 10, 10), OrderStatus.IN_PRODUCTION, ramunePetrauskene);
            orderRepository.save(ramuneOrder2);
            Order justinaOrder1 = new Order(10, "Mokyklai", true,
                    LocalDate.of(2021, 8, 31), OrderStatus.PRODUCED, justinaJonaite);
            orderRepository.save(justinaOrder1);
            Order justinaOrder2 = new Order(0, "Krikstynos", false,
                    LocalDate.of(2021, 8, 5), OrderStatus.TAKEN, justinaJonaite);
            orderRepository.save(justinaOrder2);

            Bouquet ramuneBouquet1 = new Bouquet("Rozes", 35.0, 1, 3.0, ramuneOrder1);
            bouquetRepository.save(ramuneBouquet1);
            BouquetFlower ramuneBouquetFlower1 = new BouquetFlower(24, ramuneBouquet1, rozeRaudona);
            bouquetFlowerRepository.save(ramuneBouquetFlower1);
            BouquetFlower ramuneBouquetFlower2 = new BouquetFlower(3, ramuneBouquet1, orchidejaBalta);
            bouquetFlowerRepository.save(ramuneBouquetFlower2);

            Bouquet ramuneBouquet2 = new Bouquet("Baltas vidutinis", 35.0, 2, 3.5, ramuneOrder2);
            bouquetRepository.save(ramuneBouquet2);
            BouquetFlower ramuneBouquetFlower3 = new BouquetFlower(9, ramuneBouquet2, rozeRozine);
            bouquetFlowerRepository.save(ramuneBouquetFlower3);
            BouquetFlower ramuneBouquetFlower4 = new BouquetFlower(3, ramuneBouquet2, eustomaBalta);
            bouquetFlowerRepository.save(ramuneBouquetFlower4);
            BouquetFlower ramuneBouquetFlower5 = new BouquetFlower(5, ramuneBouquet2, gvazdikaiBalta);
            bouquetFlowerRepository.save(ramuneBouquetFlower5);
            BouquetFlower ramuneBouquetFlower6 = new BouquetFlower(3, ramuneBouquet2, eustomaZalia);
            bouquetFlowerRepository.save(ramuneBouquetFlower6);
            BouquetFlower ramuneBouquetFlower7 = new BouquetFlower(20, ramuneBouquet2, zaluma);
            bouquetFlowerRepository.save(ramuneBouquetFlower7);

            Bouquet justinaBouquet1 = new Bouquet("Eustoma, gvazdikai", 25, 2, 2.5, justinaOrder1);
            bouquetRepository.save(justinaBouquet1);
            BouquetFlower justinaBouquetFlower1 = new BouquetFlower(7, justinaBouquet1, eustomaBalta);
            bouquetFlowerRepository.save(justinaBouquetFlower1);
            BouquetFlower justinaBouquetFlower2 = new BouquetFlower(12, justinaBouquet1, gvazdikaiZalia);
            bouquetFlowerRepository.save(justinaBouquetFlower2);

            Bouquet justinaBouquet2 = new Bouquet("Gvazdikai", 15, 1, 2.0, justinaOrder1);
            bouquetRepository.save(justinaBouquet2);
            BouquetFlower justinaBouquetFlower3 = new BouquetFlower(7, justinaBouquet2, gvazdikaiSakelemisBalta);
            bouquetFlowerRepository.save(justinaBouquetFlower3);
            BouquetFlower justinaBouquetFlower4 = new BouquetFlower(8, justinaBouquet2, gvazdikaiSakelemisRozine);
            bouquetFlowerRepository.save(justinaBouquetFlower4);

            Bouquet justinaBouquet3 = new Bouquet("Rozes, eustoma", 20, 2, 2.5, justinaOrder2);
            bouquetRepository.save(justinaBouquet3);
            BouquetFlower justinaBouquetFlower5 = new BouquetFlower(9, justinaBouquet3, rozeRozine);
            bouquetFlowerRepository.save(justinaBouquetFlower5);
            BouquetFlower justinaBouquetFlower6 = new BouquetFlower(6, justinaBouquet3, eustomaBalta);
            bouquetFlowerRepository.save(justinaBouquetFlower6);

            Bouquet justinaBouquet4 = new Bouquet("Vidutinis krikstynoms", 40, 1, 4.0, justinaOrder2);
            bouquetRepository.save(justinaBouquet4);
            BouquetFlower justinaBouquetFlower7 = new BouquetFlower(7, justinaBouquet4, rozeVioletine);
            bouquetFlowerRepository.save(justinaBouquetFlower7);
            BouquetFlower justinaBouquetFlower8 = new BouquetFlower(7, justinaBouquet4, rozeKremine);
            bouquetFlowerRepository.save(justinaBouquetFlower8);
            BouquetFlower justinaBouquetFlower9 = new BouquetFlower(7, justinaBouquet4, gvazdikaiZalia);
            bouquetFlowerRepository.save(justinaBouquetFlower9);
            BouquetFlower justinaBouquetFlower10 = new BouquetFlower(7, justinaBouquet4, eustomaVioletine);
            bouquetFlowerRepository.save(justinaBouquetFlower10);
            BouquetFlower justinaBouquetFlower11 = new BouquetFlower(5, justinaBouquet4, astromerijaBalta);
            bouquetFlowerRepository.save(justinaBouquetFlower11);
            BouquetFlower justinaBouquetFlower12 = new BouquetFlower(20, justinaBouquet4, zaluma);
            bouquetFlowerRepository.save(justinaBouquetFlower12);
        };
    }
}
