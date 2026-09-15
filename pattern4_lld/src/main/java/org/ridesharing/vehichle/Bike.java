package org.ridesharing.vehichle;

public class Bike implements Vehicle {

    private double farePerKm = 10.0;
    private String numberPlate;

    public Bike(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public double getFarePerKm() {
        return farePerKm;
    }

    public void setFarePerKm(double farePerKm) {
        this.farePerKm = farePerKm;
    }
}
