package com.example.demo.dto;

import com.example.demo.models.Speaker;
import com.example.demo.models.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRequestDTO {
    private String name;
    private String factory;
    private Tyre tyre;
    private Speaker speaker;
    private double price;
    private Long id;
}
