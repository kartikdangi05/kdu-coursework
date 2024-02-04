package com.kdu.smarthome.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    private String inventory;
    private HttpStatus httpStatus;
}
