package org.example.Model.Vehicle;


public abstract class Vehicle {

    private final String registrationNumber;
    private final String colour;

    public Vehicle(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public String getColour() {
        return this.colour;
    }
}
