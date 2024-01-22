package org.example.vehicleservice.services;

import org.example.vehicleservice.models.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SpeakerService {

    @Bean
    public Speaker generateSpeaker() {
        String[] brands = {"Sony", "Bose"};
        Random random = new Random();
        String brand = brands[random.nextInt(brands.length)];

        double price = (brand.equals("Sony")) ? random.nextInt(3000) + 6000.0 : random.nextInt(3000) + 8000.0;

        return new Speaker(brand, price);
    }
}