package com.example.demo.services;

import com.example.demo.models.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TyreService {
    Random random = new Random();
    public enum TyreBrand {
        BRIDGESTONE, MRF
    }
    @Bean
    public Tyre generateTyre() {
        TyreBrand brand = getRandomTyreBrand();

        double price = (brand == TyreBrand.BRIDGESTONE) ? random.nextInt(1000) + 4000.0 : random.nextInt(1000) + 3000.0;

        return new Tyre(brand.name(), price);
    }

    private TyreBrand getRandomTyreBrand() {
        TyreBrand[] brands = TyreBrand.values();
        return brands[random.nextInt(brands.length)];
    }
}
