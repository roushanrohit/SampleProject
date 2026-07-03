package org.ridesharing.actor;

import org.ridesharing.location.Location;
import org.ridesharing.vehichle.Vehicle;

public class Driver extends User {

    private Vehicle vehicle;

    public Driver(String name, Location location, Vehicle vehicle) {
        super(name, location);
        this.vehicle = vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
