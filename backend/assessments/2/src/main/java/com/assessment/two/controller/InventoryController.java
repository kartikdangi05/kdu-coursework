package com.assessment.two.controller;

import com.assessment.two.entity.Product;
import com.assessment.two.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

//    @GetMapping("/get/{id}")
//    private ResponseEntity<Product> getItem(@PathVariable Long id){
//        Product product = inventoryService.getItem(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody Product product){
        inventoryService.addItem(product);
        return new ResponseEntity<>("Product added successfully!",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody Product product){
        inventoryService.updateItem(product);
        return new ResponseEntity<>("Product updated successfully!",HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestBody Product product){
        inventoryService.deleteItem(product);
        return new ResponseEntity<>("Product deleted successfully!",HttpStatus.OK);
    }
}
