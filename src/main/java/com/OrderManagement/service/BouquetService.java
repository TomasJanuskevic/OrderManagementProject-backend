package com.OrderManagement.service;

import com.OrderManagement.exception.BouquetNotFoundException;
import com.OrderManagement.exception.UserNotFoundException;
import com.OrderManagement.model.Bouquet;
import com.OrderManagement.repository.BouquetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BouquetService {
    private final BouquetRepository bouquetRepository;

    public void addBouquet(Bouquet bouquet) {
        bouquetRepository.save(bouquet);
        log.info("Bouquet was added successfully");
    }

    public Bouquet getBouquetById(Long id) throws BouquetNotFoundException {
        return bouquetRepository.findById(id).orElseThrow(() ->
                new BouquetNotFoundException("Bouquet by id: " + id + " was not found"));

    }

    public void updateBouquet(Bouquet bouquet) {
        bouquetRepository.save(bouquet);
        log.info("Bouquet was updated successfully");
    }

    public void deleteBouquetById(Long id) throws BouquetNotFoundException {
        bouquetRepository.deleteById(id);
        log.info("Bouquet was deleted successfully");

        if (bouquetRepository.existsById(id)) {
            bouquetRepository.deleteById(id);
            log.info("Bouquet was deleted successfully");
        } else {
            throw new BouquetNotFoundException("Bouquet by id: " + id + " was not found");
        }
    }
}
