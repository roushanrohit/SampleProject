package org.ridesharing.service;

import org.ridesharing.location.Location;

public class DistanceCalculationService {

    private double calculateDistance(Location location1, Location location2){
        double dx = Math.abs(location1.getLatitude()-location2.getLatitude());
        double dy = Math.abs(location1.getLongitude()-location2.getLongitude());
        return Math.sqrt(dx*dx + dy*dy);
    }
}
