package org.example.designpatterns.behavioural.state.badcode;

public class Main {

    public static void main(String[] args) {

        DirectionService directionService = new DirectionService(TransportationMode.CAR);
        //directionService.setMode(TransportationMode.TRAIN);

        directionService.getDirection();
        System.out.println(directionService.getETA());
    }
}
