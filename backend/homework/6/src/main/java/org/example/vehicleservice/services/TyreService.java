package org.example.vehicleservice.services;

import org.example.vehicleservice.models.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TyreService {

    @Bean
    public Tyre generateTyre() {
        String[] brands = {"Bridgestone", "MRF"};
        Random random = new Random();
        String brand = brands[random.nextInt(brands.length)];

        double price = (brand.equals("Bridgestone")) ? random.nextInt(1000) + 4000.0 : random.nextInt(1000) + 3000.0;

        return new Tyre(brand, price);
    }
}