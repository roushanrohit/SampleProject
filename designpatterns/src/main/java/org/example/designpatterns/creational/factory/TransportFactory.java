package org.example.designpatterns.creational.factory;

/*
    Responsible for creating new Transport objects based on input string
    TransportService(client) do not need to now the specific details of Car, Bike or Bus
    It can simply call the methods of the Factory class
 */
public class TransportFactory {

    public static Transport createTransport(String type) throws IllegalAccessException {

        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            case "bus" -> new Bus();
            default -> throw new IllegalAccessException("Unsupported Transport Type");
        };
    }
}
