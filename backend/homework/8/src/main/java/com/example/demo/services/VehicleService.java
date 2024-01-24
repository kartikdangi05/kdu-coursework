package com.example.demo.services;

import com.example.demo.dto.VehicleRequestDTO;
import com.example.demo.dto.VehicleResponseDTO;
import com.example.demo.models.Vehicle;
import com.example.demo.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;
    public VehicleResponseDTO addVehicle(VehicleRequestDTO vehicleRequestDTO){
        System.out.println("hello");
        Vehicle newVehicle = new Vehicle(vehicleRequestDTO.getName(), vehicleRequestDTO.getFactory(),
                vehicleRequestDTO.getTyre(), vehicleRequestDTO.getSpeaker(), vehicleRequestDTO.getPrice());
        vehicleRepo.createVehicle(newVehicle);
        return new VehicleResponseDTO(newVehicle,"Vehicle added successfully! ID : ".concat(Long.toString(newVehicle.getId())),true);

    }

    public VehicleResponseDTO deleteVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRepo.deleteVehicle(vehicleId);
        if(vehicle == null){
            return new VehicleResponseDTO(null,"Vehicle not found!",false);
        }
        return new VehicleResponseDTO(vehicle,"Vehicle deleted successfully!",true);
    }

    public VehicleResponseDTO getVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRepo.getVehicleById(vehicleId);
        if(vehicle == null){
            return new VehicleResponseDTO(null,"Vehicle not found!",false);
        }
        return new VehicleResponseDTO(vehicle,"Vehicle found successfully!",true);
    }

    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO){
        Vehicle vehicle = vehicleRepo.updateVehicle(id,vehicleRequestDTO);
        if(vehicle == null){
            return new VehicleResponseDTO(null,"Vehicle not found!",false);
        }
        return new VehicleResponseDTO(vehicle, "Vehicle Updated successfully!",true);
    }

    public VehicleResponseDTO getMostAndLeastExpVehicle(String choice){
        if(choice.equals("most")){
            Vehicle vehicle = vehicleRepo.mostExpVehicle();
            if(vehicle == null){
                return new VehicleResponseDTO(null,"Vehicles list is empty!",false);
            }
            return new VehicleResponseDTO(vehicle,"Here is the most expensive vehicle!",true);
        }
        else{
            Vehicle vehicle = vehicleRepo.leastExpVehicle();
            if(vehicle == null){
                return new VehicleResponseDTO(null,"Vehicles list is empty!",false);
            }
            return new VehicleResponseDTO(vehicle,"Here is the least expensive vehicle",true);
        }
    }
}
