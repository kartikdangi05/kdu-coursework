package com.example.demo.services;

import com.example.demo.models.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SpeakerService {
    Random random = new Random();
    public enum SpeakerBrand {
        SONY, BOSE
    }
    @Bean
    public Speaker generateSpeaker() {
        SpeakerBrand speakerBrand = getRandomSpeakerBrand();

        double price = (speakerBrand == SpeakerBrand.SONY) ? random.nextInt(3000) + 6000.0 : random.nextInt(3000) + 8000.0;

        return new Speaker(speakerBrand.name(), price);
    }

    private SpeakerBrand getRandomSpeakerBrand() {
        SpeakerBrand[] brands = SpeakerBrand.values();
        return brands[random.nextInt(brands.length)];
    }
}
