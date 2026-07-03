package org.ridesharing.actor;

import org.ridesharing.location.Location;

public class Passenger {

    private String name;
    private Location location;

    public Passenger(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
