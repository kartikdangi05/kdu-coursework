package org.example.vehicleservice.services;

import org.example.vehicleservice.constant.Constant;
import org.example.vehicleservice.models.Speaker;
import org.example.vehicleservice.models.Tyre;
import org.example.vehicleservice.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("factory1")
public class VehicleService1 implements VehicleService {
    @Autowired
    public TyreService tyreService;
    @Autowired
    public SpeakerService speakerService;
    @Autowired
    public InventoryStore inventoryStore;
    @Autowired
    public CarPriceService carPriceService;
    @Autowired
    public TaxService taxService;

    @PostConstruct
    public void init() {
        List<Vehicle> vehiclesList = new ArrayList<>();
        double vehiclePrice = carPriceService.getPrice(Constant.Factory1);
        double tax = taxService.getTax(Constant.Factory1);

        for (int i = 0; i < 10; i++) {
            Tyre tyre = tyreService.generateTyre();
            Speaker speaker = speakerService.generateSpeaker();

            double amount = vehiclePrice + tyre.getPrice() + speaker.getPrice();
            double finalAmount = amount * tax + amount;
            Vehicle vehicle = new Vehicle("Vehicle".concat(Integer.toString(i + 1)), tyre, speaker,finalAmount, Constant.Factory1);
            vehiclesList.add(vehicle);
        }
        inventoryStore.getVehicles().addAll(vehiclesList);
    }

    public List<Vehicle> getVehicles(){
        return inventoryStore.getVehicles();
    }

}
