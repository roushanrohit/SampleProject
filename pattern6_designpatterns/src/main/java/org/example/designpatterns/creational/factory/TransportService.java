package org.example.designpatterns.creational.factory;

public class TransportService {

    public static void main(String[] args) {

        // Transport Service is tightly coupled with Transport Objects
        // adding new types of Transport will require modifying this code -- violation of open/close principle
//        Transport car = new Car();
//        Transport bike = new Bike();
//        Transport bus = new Bus();

        try {
            Transport bus = TransportFactory.createTransport("bus");
            bus.deliver();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
