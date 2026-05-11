package org.oops.basics.inheritance;

public class InheritanceUse {

    public static void main(String[] args) {

        /*
            Object to car ka hi ban rha h so constructors call order will be :
            Vehicle Constructor
            Geared Vehicle Constructor
            Car Constructor

            Also, a Car can do everything a vehicle can do, so this is ok
            So, we can have a superclass reference pointing to a subclass object
         */
        Vehicle v = new Car(false, 5, "Red", 200);

        /*
            Compilation happens line by line so now the compiler knows v is a Vehicle,
            so it will allow calling Vehicle class methods on it
            v.isConvertible() will give a compile time error
         */
        v.print();
        /*
            print() is there in the Vehicle class so this line of code will compile
            However, function calls take place at runtime so, print() of Car class will be executed
         */

        System.out.println();

        GearedVehicle gv = new Car(true, 6, "Yellow", 400);
        gv.print();

        System.out.println();

        Vehicle v1 = new GearedVehicle(4, "Blue", 100);
        v1.print();
    }
}
