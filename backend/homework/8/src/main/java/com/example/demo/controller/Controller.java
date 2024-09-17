package com.example.demo.controller;

import com.example.demo.dto.VehicleRequestDTO;
import com.example.demo.dto.VehicleResponseDTO;
import com.example.demo.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@AllArgsConstructor
public class Controller {

    private final VehicleService vehicleService;

    @PostMapping("/create")
    public ResponseEntity<VehicleResponseDTO> addVehicle(@RequestBody VehicleRequestDTO vehicleDto) {
        VehicleResponseDTO responseDTO = vehicleService.addVehicle(vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Long id) {
        VehicleResponseDTO responseDTO = vehicleService.getVehicle(id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO responseDTO = vehicleService.updateVehicle(id, vehicleRequestDTO);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        VehicleResponseDTO responseDTO = vehicleService.deleteVehicle(id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO.getMessage());
    }

    @GetMapping("/query/{choice}")
    public ResponseEntity<VehicleResponseDTO> getMostAndLeastExpVehicle(@PathVariable String choice) {
        VehicleResponseDTO responseDTO = vehicleService.getMostAndLeastExpVehicle(choice);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }
}
