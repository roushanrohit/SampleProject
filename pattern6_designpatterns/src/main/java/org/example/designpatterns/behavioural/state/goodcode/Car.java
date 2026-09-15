package org.example.designpatterns.behavioural.state.goodcode;

public class Car implements TransportationMode {
    @Override
    public int getETA() {
        System.out.println("Calculating ETA for Car");
        return 5;
    }

    @Override
    public void getDirection() {
        System.out.println("Calculating Direction for Car");
    }
}
