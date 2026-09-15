package org.example.designpatterns.behavioural.observer.goodcode;

public class WithObserverPattern {

    public static void main(String[] args) {

        // subscribers
        Observer o1 = new DisplayDevice();
        Observer o2 = new MobileDevice();

        // publisher
        WeatherStation station = new WeatherStation(100);

        // attach the observers to the publisher
        station.attach(o1);
        station.attach(o2);
        station.setTemperature(200);

        station.detach(o2); // mobile device is detached
        station.setTemperature(0);

    }
}
