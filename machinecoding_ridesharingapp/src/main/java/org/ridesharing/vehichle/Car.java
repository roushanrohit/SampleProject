package org.ridesharing.vehichle;

public class Car implements Vehicle {

    private static final String type = "Car";
    private String numberPlate;

    public Car(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public String getType() {
        return type;
    }
}
