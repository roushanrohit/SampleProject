package org.example.designpatterns.creational.builder;

public class Main {

    public static void main(String[] args) {

        // supply only the mandatory parameters, also the order of optional parameters is not important
        House house = new House.HouseBuilder("foundation","structure","roof")
                .setGarden(true)
                .build();

        System.out.println(house);

    }
}
