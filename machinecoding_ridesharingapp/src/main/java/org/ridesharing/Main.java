package org.ridesharing;

import org.ridesharing.actor.Driver;
import org.ridesharing.actor.Passenger;
import org.ridesharing.location.Location;
import org.ridesharing.vehichle.Bike;
import org.ridesharing.vehichle.Car;

/*
    You are tasked with designing and implementing a ride-sharing application where passengers can request rides,
    and drivers can be matched to them based on proximity.
    The application should handle different types of vehicles (such as cars, bikes, luxury cars)
    and support multiple fare calculation strategies.
    The system must notify both passengers and drivers about ride statuses and calculate the fare
    based on the type of ride and distance traveled.

    1. The ride-matching algorithm must assign the nearest driver based on the distance between the passenger and driver.
    2. You must implement at least three fare calculation strategies: Standard, Shared, and Luxury.
    3. The system should be able to support different vehicle types: Car and Bike (with the option to extend for future vehicle types).
    4. Notifications must be sent to both the passenger and driver as the ride progresses through different statuses (pending, ongoing, completed).
 */
public class Main {
    public static void main(String[] args) {

        Location loc1 = new Location(12.9232,13.2726);
        Location loc2 = new Location(18.9002,23.1233);
        Location loc3 = new Location(32.8001,33.1002);
        Location loc4 = new Location(20.9232,13.2726);

        Car car = new Car("BR263251");
        Bike bike = new Bike("KA01JS9803");

        Driver dv1 = new Driver("Rohit",loc1,car);
        Driver dv2 = new Driver("Rahul",loc2,bike);

        Passenger p1 = new Passenger("Passenger1", loc3);
        Passenger p2 = new Passenger("Passenger2", loc4);

        // we need to register all the drivers somewhere so that we're able to find the most
        // suitable driver for a passenger
        // once a driver is booked, we need to remove it from the list of available drivers until
        // the ride is complete -- keep a status
    }
}