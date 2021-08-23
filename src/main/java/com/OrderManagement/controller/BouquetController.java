package com.OrderManagement.controller;

import com.OrderManagement.exception.BouquetNotFoundException;
import com.OrderManagement.model.Bouquet;
import com.OrderManagement.service.BouquetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BouquetController {
    private final BouquetService bouquetService;

    @GetMapping("/bouquet/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Bouquet> getBouquetById(@PathVariable Long id) throws BouquetNotFoundException {
        return new ResponseEntity<>(bouquetService.getBouquetById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/bouquet")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addBouquet(@RequestBody Bouquet bouquet) {
        bouquetService.addBouquet(bouquet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/bouquet")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateBouquet(@RequestBody Bouquet bouquet) {
        bouquetService.updateBouquet(bouquet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/bouquet/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteBouquetById(@PathVariable Long id) throws BouquetNotFoundException {
        bouquetService.deleteBouquetById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
