package org.example.designpatterns.behavioural.state.goodcode;

public class Train implements TransportationMode {
    @Override
    public int getETA() {
        System.out.println("Calculating ETA for Train");
        return 15;
    }

    @Override
    public void getDirection() {
        System.out.println("Calculating Direction for Train");
    }
}
