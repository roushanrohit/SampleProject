package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Restaurant a2b = new Restaurant("A2B", 4);
        a2b.addItem(new Item("Idly",40.0));
        a2b.addItem(new Item("Vada", 30.0));
        a2b.addItem(new Item("Paper Dosa", 50.0));

        Restaurant rasaganga = new Restaurant("Rasaganga", 6);
        rasaganga.addItem(new Item("Idly",45.0));
        rasaganga.addItem(new Item("Set Dosa", 60.0));
        rasaganga.addItem(new Item("Poori", 25.0));

        List<String> itemsToOrder = new ArrayList<>();
        itemsToOrder.add("Idly");
        itemsToOrder.add("Vada");

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(a2b);
        restaurantList.add(rasaganga);

        PricingStrategy pricingStrategy = new LowestPriceStrategy();
        Order orderCreated = pricingStrategy.createOrder(restaurantList, itemsToOrder);
        System.out.println("Order created: " + orderCreated);
    }
}