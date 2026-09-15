package org.ridesharing.fare;

import org.ridesharing.vehichle.Vehicle;

public interface FareStrategy {

    double calcfare(Vehicle vehicle, double distance);
}
