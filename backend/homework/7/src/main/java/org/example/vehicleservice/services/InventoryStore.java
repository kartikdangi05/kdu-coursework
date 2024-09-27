package org.example.vehicleservice.services;

import org.example.vehicleservice.models.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class InventoryStore {
    List<Vehicle> vehicleList ;

    public InventoryStore(){
        this.vehicleList = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    public List<Vehicle> getVehicles(){
        return vehicleList;
    }

}
