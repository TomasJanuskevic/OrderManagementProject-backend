package com.OrderManagement.service;

import com.OrderManagement.model.BouquetFlower;
import com.OrderManagement.repository.BouquetFlowerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BouquetFlowerService {
    private final BouquetFlowerRepository bouquetFlowerRepository;

    public void addBouquetFlower(BouquetFlower bouquetFlower) {
        bouquetFlowerRepository.save(bouquetFlower);
        log.info("BouquetFlower was added successfully");
    }

    public void deleteBouquetFlowerById(Long id) {
        bouquetFlowerRepository.deleteById(id);
        log.info("BouquetFlower was deleted successfully");
    }
}
