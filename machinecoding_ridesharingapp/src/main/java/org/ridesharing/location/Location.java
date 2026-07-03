package org.ridesharing.location;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static double calculateDistance(Location location1, Location location2){
        double dx = Math.abs(location1.getLatitude()-location2.getLatitude());
        double dy = Math.abs(location1.getLongitude()-location2.getLongitude());
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
