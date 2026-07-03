package org.ridesharing.service;

import org.ridesharing.vehichle.Vehicle;

public class FareCalculationService {

    private double calcFare(Vehicle vehicle, int distance){

        // hardcoded -- need to modify for a new vehicle -- violates OCP
        return switch (vehicle.getType()) {
            case "Bike" -> distance * 20;
            case "Car" -> distance * 30;
            default -> distance * 8; // minimum fare
        };
    }
}
