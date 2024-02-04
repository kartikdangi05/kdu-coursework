package com.kdu.smarthome.mapping;

import com.kdu.smarthome.dto.inventory.InventoryRequestDTO;
import com.kdu.smarthome.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapping {

    public Inventory inventoryMapping(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory = new Inventory();
        inventory.setKickstonId(inventoryRequestDTO.getKickstonId());
        inventory.setDeviceUsername(inventoryRequestDTO.getDeviceUsername());
        inventory.setDevicePassword(inventoryRequestDTO.getDevicePassword());
        inventory.setManufactureDateTime(inventoryRequestDTO.getManufactureDateTime());
        inventory.setManufactureFactoryPlace(inventoryRequestDTO.getManufactureFactoryPlace());

        return inventory;
    }
}
