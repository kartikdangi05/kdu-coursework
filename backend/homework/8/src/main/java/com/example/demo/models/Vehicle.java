package com.example.demo.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Entity;
@Entity
@Data
public class Vehicle {

    private static Long nextId = 1L;
    private Long id;
    private String name;
    private String factory;
    private Tyre tyre;
    private Speaker speaker;
    private double price;

    @Autowired
    public Vehicle(String name, String factory, Tyre tyre, Speaker speaker, double price) {
        this.id = nextId++;
        this.name = name;
        this.factory = factory;
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price;
    }


}