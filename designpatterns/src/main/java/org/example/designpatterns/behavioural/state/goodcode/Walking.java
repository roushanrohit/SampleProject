package org.example.designpatterns.behavioural.state.goodcode;

public class Walking implements TransportationMode {
    @Override
    public int getETA() {
        System.out.println("Calculating ETA for Walking");
        return 7;
    }

    @Override
    public void getDirection() {
        System.out.println("Calculating Direction for Walking");
    }
}
