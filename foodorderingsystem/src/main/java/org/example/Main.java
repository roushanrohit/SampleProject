package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        // order processing service
        OrderProcessingService orderProcessingService = new OrderProcessingService();

        Runnable task = () -> {

            Order orderCreated = pricingStrategy.createOrder(restaurantList, getRandomItemNames());
            if(orderCreated == null){
                System.out.println(Thread.currentThread().getName() + " -> Some items may not be available or restaurants are full");
            } else {
                System.out.println(Thread.currentThread().getName() + " -> " + orderCreated);
                orderProcessingService.processOrder(orderCreated);
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

    private static List<String> getRandomItemNames() {
        List<String> allItems = new ArrayList<>(List.of(
                "Idly",
                "Vada",
                "Paper Dosa",
                "Set Dosa",
                "Poori"
        ));
        Collections.shuffle(allItems);
        int itemCount = 1 + new Random().nextInt(3);
        return new ArrayList<>(allItems.subList(0, itemCount));
    }
}