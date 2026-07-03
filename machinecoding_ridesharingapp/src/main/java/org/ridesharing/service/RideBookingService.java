package org.ridesharing.service;

import org.ridesharing.actor.Driver;
import org.ridesharing.actor.Passenger;
import org.ridesharing.fare.FareStrategy;
import org.ridesharing.location.Location;

import java.util.ArrayList;
import java.util.List;

public class RideBookingService {

    private List<Driver> availableDrivers = new ArrayList<>();

    public RideBookingService(FareStrategy fareStrategy){
        availableDrivers = new ArrayList<>();
    }

    public List<Driver> getAvailableDrivers() {
        return availableDrivers;
    }

    public void addDriver(Driver driver){
        availableDrivers.add(driver);
    }

    private void bookRide(Passenger passenger, double distance, FareStrategy fareStrategy){

        // edge case
        if(availableDrivers.isEmpty()){
            passenger.notify("No drivers are available");
        } else {
            // find the nearest driver available -- we can add a radius limit also here...
            Driver driver = findNearestDriver(passenger.getLocation());
            passenger.notify("Ride scheduled successfully with rider: " + driver.getName());
        }
    }

    private Driver findNearestDriver(Location location) {
        Driver assignedDriver = null;
        double minDist = Double.MAX_VALUE;
        for(Driver driver : availableDrivers){
            double dist = Location.calculateDistance(driver.getLocation(), location);
            if(dist < minDist){
                minDist = dist;
                assignedDriver = driver;
            }
        }
        return assignedDriver;
    }
}
