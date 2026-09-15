package org.example.designpatterns.creational.factory;

public class Bike implements Transport{

    @Override
    public void deliver() {
        System.out.println("Deliver by Bike");
    }
}
