package org.oops.basics.inheritance;

import java.lang.reflect.Field;

public class Car extends GearedVehicle {

    boolean isConvertible;

    public Car(boolean isConvertible, int noOfGears, String color, int maxSpeed){
        super(noOfGears, color, maxSpeed);
        System.out.println("Inside Car Constructor");
        this.isConvertible = isConvertible;
    }

    public boolean isConvertible() {
        return isConvertible;
    }

    public void print(){
        super.print();
        System.out.println("Car color: " + color);
        // Car has maxSpeed but can't access it directly
        System.out.println("Car max speed: " + getMaxSpeed());
        System.out.println("Car no of gears: " + getNoOfGears());
        System.out.println("Car is convertible: " + isConvertible);

        System.out.println("Printing all fields (including inherited fields: ");
        Class<?> clazz = Car.class;
        while (clazz != null) {
            System.out.print(clazz.getName() + " : ");
            for (Field field : clazz.getDeclaredFields()) {
                System.out.print(field.getName() + " ");
            }
            System.out.println();
            clazz = clazz.getSuperclass();
        }
        /*
            For object class nothing wil be printed as it doesn't have any fields of its own
            It only defines a few methods such as toString(),
            hashCode(), equals(), wait(), notify(), etc., but no fields are declared in it.
            Superclass of Object class will be null and the loop will exit.
         */
    }
}
