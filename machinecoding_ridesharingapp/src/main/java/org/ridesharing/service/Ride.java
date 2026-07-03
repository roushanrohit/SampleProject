package org.ridesharing.service;

import org.ridesharing.actor.Driver;
import org.ridesharing.actor.Passenger;
import org.ridesharing.fare.FareStrategy;
import org.ridesharing.vehichle.Vehicle;

public class Ride {

    private Passenger passenger;
    private Driver driver;
    private double distance;
    private FareStrategy fareStrategy;
    private double fare;
    private RideStatus status;

    public Ride(Passenger passenger, Driver driver, double distance, FareStrategy fareStrategy) {
        this.passenger = passenger;
        this.driver = driver;
        this.distance = distance;
        this.fareStrategy = fareStrategy;
        calcFare(driver.getVehicle(), distance);
        this.status = RideStatus.SCHEDULED;
    }

    public void calcFare(Vehicle vehicle, double distance) {
        this.fare =  fareStrategy.calcfare(vehicle,distance);
    }

    // updates are sent to both driver and passenger using the observer pattern
    public void updateStatus(RideStatus status){
        this.status = status;
        passenger.notify("Your ride is : " + status);
        driver.notify("Ride status : " + status);
    }

    public double getFare() {
        return fare;
    }
}
