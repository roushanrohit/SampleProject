package org.ridesharing.actor;

import org.ridesharing.location.Location;

public abstract class User {

    protected String name;
    protected Location location;

    public User(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    // declared here ... since it is common to passenger and driver both
    public abstract void notify(String msg);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
