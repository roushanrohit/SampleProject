package org.example;

import java.util.*;

public class LowestPriceStrategy implements PricingStrategy {

    @Override
    public Order createOrder(List<Restaurant> restaurants, List<String> itemNames) {

        Map<Restaurant, List<Item>> orderMap = new HashMap<>();

        for(String itemName : itemNames){

            // Get all restaurants selling this item
            List<Restaurant> candidateRestaurants =
                    restaurants.stream()
                            .filter(r -> r.getItem(itemName) != null)
                            .sorted(Comparator.comparingDouble(r -> r.getItem(itemName).getPrice()))
                            .toList();

            // Try restaurants from cheapest to costliest
            for(Restaurant restaurant : candidateRestaurants){

                if(restaurant.tryReserveSlot()){
                    try {
                        Item item = restaurant.getItem(itemName);
                        // simulate processing
                        Thread.sleep(1000);
                        orderMap.computeIfAbsent(restaurant, r -> new ArrayList<>()).add(item);

                        // successfully assigned
                        break;

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        restaurant.releaseSlot();
                    }
                }
            }
        }

        int assignedItemsCount = orderMap.values()
                .stream()
                .mapToInt(List::size)
                .sum();
        if(assignedItemsCount < itemNames.size()){
            // some items are either not available or restaurants are not having enough capacity
            return null;
        }
        return new Order(orderMap, generateOrderId());
    }

    private int generateOrderId(){
        return new Random().nextInt(100000);
    }
}
