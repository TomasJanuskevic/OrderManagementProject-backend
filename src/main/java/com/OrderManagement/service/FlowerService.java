package com.OrderManagement.service;

import com.OrderManagement.exception.FlowerNotFoundException;
import com.OrderManagement.model.Flower;
import com.OrderManagement.repository.FlowerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlowerService {

    private final FlowerRepository flowerRepository;

    public Flower getFlowerById(Long id) throws FlowerNotFoundException {
        return flowerRepository.findById(id)
                .orElseThrow(()-> new FlowerNotFoundException("Flower by id: " + id + " was not found"));
    }

    public void addFlower(Flower flower) {
        flowerRepository.save(flower);
        log.info("Flower was added successfully");
    }

    public void updateFlower(Flower flower) throws FlowerNotFoundException {
        if(flowerRepository.existsById(flower.getFlowerId())){
            flowerRepository.save(flower);
            log.info("Flower was updated successfully");
        } else{
            throw new FlowerNotFoundException("Flower by id: " + flower.getFlowerId() + " was not found");
        }
    }

    public void deleteFlowerById(Long id) throws FlowerNotFoundException {
        if(flowerRepository.existsById(id)){
            flowerRepository.deleteById(id);
            log.info("Flower was deleted successfully");
        } else {
            throw new FlowerNotFoundException("Flower by id: " + id + " was not found");
        }
    }
}
