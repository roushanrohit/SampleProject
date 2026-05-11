package org.oops.basics.inheritance;

public abstract class Vehicle {

    String color;
    private int maxSpeed;

    public abstract boolean isMotorized();
    public Vehicle(String color, int maxSpeed){
        System.out.println("Inside Vehicle Constructor");
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public void print(){
        System.out.println("Vehicle color: " + color);
        System.out.println("Vehicle max speed: " + maxSpeed);
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
