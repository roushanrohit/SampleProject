package org.ridesharing.vehichle;

public class Car implements Vehicle {

    private double farePerKm = 20.0;
    private String numberPlate;

    public Car(String numberPlate) {
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
