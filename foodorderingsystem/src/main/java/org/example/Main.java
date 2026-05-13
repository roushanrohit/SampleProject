package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Restaurant a2b = new Restaurant("A2B", 2);
        a2b.addItem(new Item("Idly", 40.0));
        a2b.addItem(new Item("Vada", 30.0));
        a2b.addItem(new Item("Paper Dosa", 50.0));

        Restaurant rasaganga = new Restaurant("Rasaganga", 3);
        rasaganga.addItem(new Item("Idly", 30.0));
        rasaganga.addItem(new Item("Set Dosa", 60.0));
        rasaganga.addItem(new Item("Poori", 25.0));

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(a2b);
        restaurantList.add(rasaganga);

        // choose a pricing strategy
        PricingStrategy pricingStrategy = new LowestPriceStrategy();

        Runnable task = () -> {

            List<String> itemsToOrder = new ArrayList<>();
            itemsToOrder.add("Idly");
            itemsToOrder.add("Vada");

            Order orderCreated = pricingStrategy.createOrder(restaurantList, itemsToOrder);
            synchronized (System.out) {
                if(orderCreated == null){
                    System.out.println(Thread.currentThread().getName() + " -> Some items may not be available or restaurants are full");
                } else {
                    System.out.println(Thread.currentThread().getName() + " -> " + orderCreated);
                }
            }
        };

        // Fire multiple concurrent requests
        List<Thread> threads = new ArrayList<>();

        for(int i = 1; i <= 10; i++){

            Thread thread = new Thread(task, "User-" + i);
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for(Thread thread : threads){

            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All requests completed.");
    }
}