package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.inventory.InventoryRequestDTO;
import com.kdu.smarthome.dto.inventory.InventoryResponseDTO;
import com.kdu.smarthome.dto.ResponseInfoDTO;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Adds the item to the inventory
     * @param inventoryRequestDTO
     * @return
     */
    @PostMapping("/api/v1/inventory")
    public ResponseEntity<ResponseInfoDTO> addItem(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        ResponseInfoDTO responseInfoDTO = inventoryService.addItem(inventoryRequestDTO);
        return new ResponseEntity<>(responseInfoDTO, HttpStatus.OK);
    }

    /**
     * Fetches all the items from the inventory
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/api/v1/inventory")
    public ResponseEntity<InventoryResponseDTO> getItem() throws JsonProcessingException {
        InventoryResponseDTO inventoryResponseDTO = inventoryService.getItems();
        return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
    }
}
