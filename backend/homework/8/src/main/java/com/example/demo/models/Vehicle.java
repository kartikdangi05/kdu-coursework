package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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