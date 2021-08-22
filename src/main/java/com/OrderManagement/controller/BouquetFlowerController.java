package com.OrderManagement.controller;

import com.OrderManagement.model.BouquetFlower;
import com.OrderManagement.service.BouquetFlowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BouquetFlowerController {
    private final BouquetFlowerService bouquetFlowerService;

    @PostMapping(value="/bouquetFlower")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addBouquetFlower(@RequestBody BouquetFlower bouquetFlower) {
        bouquetFlowerService.addBouquetFlower(bouquetFlower);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/bouquetFlower/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteBouquetFlowerById(@PathVariable Long id) {
        bouquetFlowerService.deleteBouquetFlowerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
