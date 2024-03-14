package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.inventory.InventoryRequestDTO;
import com.kdu.smarthome.dto.inventory.InventoryResponseDTO;
import com.kdu.smarthome.dto.ResponseInfoDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.mapping.InventoryMapping;
import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapping inventoryMapping;
    private final JSONUtil jsonUtil;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository,
                            InventoryMapping inventoryMapping,
                            JSONUtil jsonUtil) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapping = inventoryMapping;
        this.jsonUtil = jsonUtil;
    }

    /**
     * Adds a item to the inventory
     * @param inventoryRequestDTO
     * @return
     */
    public ResponseInfoDTO addItem(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory = inventoryMapping.inventoryMapping(inventoryRequestDTO);
        inventoryRepository.save(inventory);
        return new ResponseInfoDTO("Device added successfully!","Kickston ID : ".concat(inventory.getKickstonId()), HttpStatus.OK);
    }

    /**
     * Fetches all the items of inventory
     * @return
     * @throws JsonProcessingException
     */
    public InventoryResponseDTO getItems() throws JsonProcessingException {
        List<Inventory> inventories = inventoryRepository.findAll();
        String inventoryJSON = jsonUtil.convertListToJSONString(inventories);
        return new InventoryResponseDTO(inventoryJSON,HttpStatus.OK);
    }
}
