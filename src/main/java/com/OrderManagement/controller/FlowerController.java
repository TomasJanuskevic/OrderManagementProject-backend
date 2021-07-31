package com.OrderManagement.controller;

import com.OrderManagement.exception.FlowerNotFoundException;
import com.OrderManagement.model.Flower;
import com.OrderManagement.service.FlowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping("/flowers")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Flower>> findFlowers() {
        return new ResponseEntity<>(flowerService.findFlowers(), HttpStatus.OK);
    }

    @GetMapping("/flower/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Flower> getFlowerById(@PathVariable Long id) throws FlowerNotFoundException {
        return new ResponseEntity<>(flowerService.getFlowerById(id), HttpStatus.OK);
    }

    @PostMapping("/flower")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addFlower(@RequestBody Flower flower) {
        flowerService.addFlower(flower);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/flower")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateFlower(@RequestBody Flower flower) throws FlowerNotFoundException {
        flowerService.updateFlower(flower);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/flower/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws FlowerNotFoundException {
        flowerService.deleteFlowerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
