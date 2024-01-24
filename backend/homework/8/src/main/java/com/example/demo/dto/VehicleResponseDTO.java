package com.example.demo.dto;

import com.example.demo.models.Speaker;
import com.example.demo.models.Tyre;
import com.example.demo.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class VehicleResponseDTO {
    private String name;
    private String factory;
    private Tyre tyre;
    private Speaker speaker;
    private double price;
    private Long id;
    private String message;
    private boolean success;

    public VehicleResponseDTO(Vehicle vehicle, String message,boolean success) {
        if (vehicle != null) {
            this.id = vehicle.getId();
            this.factory = vehicle.getFactory();
            this.tyre = vehicle.getTyre();
            this.speaker = vehicle.getSpeaker();
            this.price = vehicle.getPrice();
            this.name = vehicle.getName();
            this.success = success;
        }
        this.message = message;
    }
}
