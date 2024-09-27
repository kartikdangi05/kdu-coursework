package org.example.vehicleservice.utils;

import org.example.vehicleservice.models.Vehicle;

import java.util.List;

public class MostExpAndCheap {

    private MostExpAndCheap(){}
    public static Vehicle getMostExpensiveVehicle(List<Vehicle> vehicles){
        return vehicles.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }

    public static Vehicle getCheapestVehicle(List<Vehicle> vehicles){
        return vehicles.stream()
                .min((v1,v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }
}
