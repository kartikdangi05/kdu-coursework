package com.assessment.two.service;

import com.assessment.two.entity.Product;
import com.assessment.two.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public void addItem(Product product){
        inventoryRepo.save(product);
    }

    public void updateItem(Product product){
        inventoryRepo.save(product);
    }

    public void deleteItem(Product product){
        inventoryRepo.delete(product);
    }
}
