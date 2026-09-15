package org.example.designpatterns.behavioural.state.goodcode;

public class Main {

    public static void main(String[] args) {

        DirectionService directionService = new DirectionService(new Car());
        directionService.setMode(new Train());

        directionService.getDirection();
        System.out.println(directionService.getETA());
    }
}
