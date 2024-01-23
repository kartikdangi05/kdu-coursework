package org.example.vehicleservice.services;

import org.example.vehicleservice.models.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TyreService {
    Random random = new Random();
    @Bean
    public Tyre generateTyre() {
        String[] brands = {"Bridgestone", "MRF"};
        String brand = brands[random.nextInt(brands.length)];

        double price = (brand.equals("Bridgestone")) ? random.nextInt(1000) + 4000.0 : random.nextInt(1000) + 3000.0;

        return new Tyre(brand, price);
    }
}