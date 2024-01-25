package com.example.demo.repository;

import com.example.demo.dto.VehicleRequestDTO;
import com.example.demo.models.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class VehicleRepo {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void createVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public Vehicle getVehicleById(Long id){
        for(Vehicle vehicle : vehicles){
            if(Objects.equals(vehicle.getId(), id)){
                return vehicle;
            }
        }
        return null;
    }

    public Vehicle deleteVehicle(Long id){
        for(Vehicle vehicle : vehicles){
            if(Objects.equals(vehicle.getId(), id)){
                vehicles.remove(vehicle);
                return vehicle;
            }
        }
        return null;
    }

    public Vehicle updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO){
        for(Vehicle vehicle : vehicles){
            if(Objects.equals(vehicle.getId(), id)){
                vehicle.setName(vehicleRequestDTO.getName());
                vehicle.setPrice(vehicleRequestDTO.getPrice()
                        + vehicleRequestDTO.getTyre().getPrice() + vehicleRequestDTO.getSpeaker().getPrice());
                vehicle.setFactory(vehicleRequestDTO.getFactory());
                vehicle.setId(vehicleRequestDTO.getId());
                vehicle.setSpeaker(vehicleRequestDTO.getSpeaker());
                vehicle.setTyre(vehicleRequestDTO.getTyre());
                vehicle.setId(vehicleRequestDTO.getId());

                return vehicle;
            }
        }
        return null;
    }

    public Vehicle mostExpVehicle(){
        return vehicles.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }

    public Vehicle leastExpVehicle(){
        return vehicles.stream()
                .min((v1,v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }
}
