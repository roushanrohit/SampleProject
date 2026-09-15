package org.example.designpatterns.behavioural.state.badcode;

/*
    Without State Pattern:
    1. As the number of modes increase, the conditional logic is going to become more complex and bulky
    2. Violates Open Closed principle -- new mode added, needs to modify already tested DirectionService class
    3. Code duplication
 */
public class DirectionService {

    private TransportationMode mode;

    public DirectionService(TransportationMode mode){
        this.mode = mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    public int getETA(){
        switch (mode) {
            case WALKING -> {
                System.out.println("Calculating ETA for Walking");
                return 25;
            }
            case CAR -> {
                System.out.println("Calculating ETA for Car");
                return 7;
            }
            case TRAIN -> {
                System.out.println("Calculating ETA for Train");
                return 15;
            }
            case CYCLING -> {
                System.out.println("Calculating ETA for Cycling");
                return 15;
            }
            default -> {
                return -1;
            }
        }
    }

    public void getDirection(){
        switch (mode) {
            case WALKING -> {
                System.out.println("Calculating Direction for Walking");
            }
            case CAR -> {
                System.out.println("Calculating Direction for Car");
            }
            case TRAIN -> {
                System.out.println("Calculating Direction for Train");
            }
            case CYCLING -> {
                System.out.println("Calculating Direction for Cycling");
            }
        }
    }
}
