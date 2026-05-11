package org.example.designpatterns.creational.factory;

public class Bus implements Transport{

    @Override
    public void deliver() {
        System.out.println("Deliver by Bus");
    }
}
