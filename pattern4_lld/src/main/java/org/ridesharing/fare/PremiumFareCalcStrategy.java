package org.ridesharing.fare;

import org.ridesharing.vehichle.Vehicle;

public class PremiumFareCalcStrategy implements FareStrategy {

    @Override
    public double calcfare(Vehicle vehicle, double distance) {
        // additional 50% charge for luxury ride
        return vehicle.getFarePerKm() * distance * 1.5;
    }
}
