package org.example.vehicleservice.models;
public class Vehicle {

    private String name;
    private Tyre tyre;
    private Speaker speaker;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle(String name, Tyre tyre, Speaker speaker, double price) {
        this.name = name;
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price;
    }

}

