package org.ridesharing.fare;

import org.ridesharing.vehichle.Vehicle;

public class StandardFareCalcStrategy implements FareStrategy {

    @Override
    public double calcfare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm() * distance;
    }
}
