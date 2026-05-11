package org.oops.basics.inheritance;

public class GearedVehicle extends Vehicle {

    private int noOfGears;

    public GearedVehicle(int noOfGears, String color, int maxSpeed){
        super(color, maxSpeed);
        System.out.println("Inside Geared Vehicle Constructor");
        this.noOfGears = noOfGears;
    }

    @Override
    public boolean isMotorized() {
        return false;
    }

    public void print(){
        super.print();
        System.out.println("Geared Vehicle color: " + color);
        // Geared Vehicle has maxSpeed but can't access it directly
        System.out.println("Geared Vehicle max speed: " + getMaxSpeed());
        System.out.println("Geared Vehicle no of gears: " + noOfGears);
    }

    public int getNoOfGears(){
        return noOfGears;
    }

    public void setNoOfGears(int noOfGears){
        this.noOfGears = noOfGears;
    }
}
