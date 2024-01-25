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

    /**
     * POST request to add vehicle
     * @param vehicleDto
     * @return vehicleResponseDTO
     */
    @PostMapping("/")
    public ResponseEntity<VehicleResponseDTO> addVehicle(@RequestBody VehicleRequestDTO vehicleDto) {
        VehicleResponseDTO responseDTO = vehicleService.addVehicle(vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    /**
     * GET request to get vehicle from id
     * @param id
     * @return vehicleResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Long id) {
        VehicleResponseDTO responseDTO = vehicleService.getVehicle(id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }

    /**
     * PUT request to update vehicle
     * @param id
     * @param vehicleRequestDTO
     * @return vehicleResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO responseDTO = vehicleService.updateVehicle(id, vehicleRequestDTO);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }

    /**
     * DELETE request to delete vehicle from its id
     * @param id
     * @return String representing message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        VehicleResponseDTO responseDTO = vehicleService.deleteVehicle(id);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO.getMessage());
    }

    /**
     * GET request to get the Most/Least Expensive vehicle from choice
     * @param choice -> can be "most" and "least"
     * @return vehicleResponseDTO
     */
    @GetMapping("/query/{choice}")
    public ResponseEntity<VehicleResponseDTO> getMostAndLeastExpVehicle(@PathVariable String choice) {
        VehicleResponseDTO responseDTO = vehicleService.getMostAndLeastExpVehicle(choice);
        return ResponseEntity.status(responseDTO.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(responseDTO);
    }
}
