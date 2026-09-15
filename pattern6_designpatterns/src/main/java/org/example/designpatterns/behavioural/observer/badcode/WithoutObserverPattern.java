package org.example.designpatterns.behavioural.observer.badcode;

public class WithoutObserverPattern {

    public static void main(String[] args) {

        // devices are tightly coupled with the weather station
        DisplayDevice device = new DisplayDevice();
        WeatherStation station = new WeatherStation(device);

        station.setTemperature(100f);
    }
}
