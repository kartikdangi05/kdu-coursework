package org.example.vehicleservice.services;

import org.example.vehicleservice.models.Speaker;
import org.example.vehicleservice.models.Tyre;
import org.example.vehicleservice.models.Vehicle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private TyreService tyreService;

    private SpeakerService speakerService;

    private List<Vehicle> vehicles;

    @PostConstruct
    public void init() {
        vehicles = new ArrayList<>();
        double vehiclePrice = 1000.0;

        for(int i = 0; i < 10; i++){
            Tyre tyre = tyreService.generateTyre();
            Speaker speaker = speakerService.generateSpeaker();
            Vehicle vehicle = new Vehicle("Vehicle".concat(Integer.toString(i+1)),tyre,speaker,vehiclePrice +
                    tyre.getPrice() + speaker.getPrice());
            vehicles.add(vehicle);
        }

    }

    public Vehicle findMostExpensiveVehicle() {
        return vehicles.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
