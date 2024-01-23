package org.example.vehicleservice.models;
public class Vehicle {

    private String name;
    private String factory;
    private Tyre tyre;
    private Speaker speaker;
    private double price;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

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

    public Vehicle(String name, Tyre tyre, Speaker speaker, double price, String factory) {
        this.name = name;
        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price;
        this.factory = factory;
    }

}

