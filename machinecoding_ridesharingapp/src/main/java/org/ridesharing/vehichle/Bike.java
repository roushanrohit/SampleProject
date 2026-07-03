package org.ridesharing.vehichle;

public class Bike implements Vehicle {

    private static final String type = "Bike";
    private String numberPlate;

    public Bike(String numberPlate) {
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
