package org.example;


import logger.Logging;
import org.example.vehicleservice.config.AppConfig;
import org.example.vehicleservice.models.Vehicle;
import org.example.vehicleservice.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] arg) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

        List<Vehicle> vehicles = vehicleService.getVehicles();
        for(Vehicle vehicle : vehicles){
            Logging.logInfo(vehicle.getName().concat(" , Price : ").concat(Double.toString(vehicle.getPrice())));
        }
        Logging.logInfo("\nMost expensive vehicle : ");
        Vehicle mostExpensiveVehicle = vehicleService.findMostExpensiveVehicle();
        Logging.logInfo(mostExpensiveVehicle.getName().concat(" , Price : ").concat(Double.toString(mostExpensiveVehicle.getPrice())));
    }
}