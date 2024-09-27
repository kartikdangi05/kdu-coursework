package org.example;


import logger.Logging;
import org.example.vehicleservice.config.AppConfig;
import org.example.vehicleservice.models.Vehicle;
import org.example.vehicleservice.services.VehicleService;
import org.example.vehicleservice.utils.MostExpAndCheap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Main {

    private final VehicleService vehicleService1;
    private final VehicleService vehicleService2;

    @Autowired
    public Main(@Qualifier("factory1") VehicleService vehicleService1,
                @Qualifier("factory2") VehicleService vehicleService2) {
        this.vehicleService1 = vehicleService1;
        this.vehicleService2 = vehicleService2;

    }

    public void run() {
        List<Vehicle> vehiclesFactory1 = vehicleService1.getVehicles();
        List<Vehicle> vehiclesFactory2 = vehicleService2.getVehicles();

        Logging.logInfo("Factory 1 Vehicles -> ");
        for (Vehicle vehicle : vehiclesFactory1) {
            Logging.logInfo(vehicle.getName().concat(" , Price : ").concat(Double.toString(vehicle.getPrice())));
        }

        Logging.logInfo("Factory 2 Vehicles -> ");
        for (Vehicle vehicle : vehiclesFactory2) {
            Logging.logInfo(vehicle.getName().concat(" , Price : ").concat(Double.toString(vehicle.getPrice())));
        }

        Logging.logInfo("\nMost expensive vehicle From Factory 1 : ");
        Vehicle mostExpensiveVehicle1 = MostExpAndCheap.getMostExpensiveVehicle(vehiclesFactory1);
        Vehicle mostExpensiveVehicle2 = MostExpAndCheap.getMostExpensiveVehicle(vehiclesFactory2);

        Vehicle mostExpensive = mostExpensiveVehicle1.getPrice() > mostExpensiveVehicle2.getPrice() ? mostExpensiveVehicle1 : mostExpensiveVehicle2;
        Logging.logInfo(mostExpensive.getName().concat(" , Price : ").concat(Double.toString(mostExpensive.getPrice())));

        Logging.logInfo("\nLeast expensive vehicle From Factory 1 : ");
        Vehicle cheapestVehicle1 = MostExpAndCheap.getCheapestVehicle(vehiclesFactory1);
        Vehicle cheapestVehicle2 = MostExpAndCheap.getCheapestVehicle(vehiclesFactory2);

        Vehicle cheapest = cheapestVehicle1.getPrice() < cheapestVehicle2.getPrice() ? cheapestVehicle1 : cheapestVehicle2;
        Logging.logInfo(cheapest.getName().concat(" , Price : ").concat(Double.toString(cheapest.getPrice())));
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Main main = context.getBean(Main.class);
            main.run();
        }
    }
}