package org.example;

import java.util.*;

public class LowestPriceStrategy implements PricingStrategy {

    @Override
    public Order createOrder(List<Restaurant> allRestaurants, List<String> itemNames) {

        Map<Restaurant, List<Item>> orderMap = new HashMap<>();

        for(String itemName : itemNames){

            // Get all restaurants selling this item
            List<Restaurant> candidateRestaurants =
                    allRestaurants.stream()
                            .filter(r -> r.getItem(itemName) != null)
                            .sorted(Comparator.comparingDouble(r -> r.getItem(itemName).getPrice()))
                            .toList();

            // Try restaurants from cheapest to costliest
            for(Restaurant restaurant : candidateRestaurants){

                if(orderMap.containsKey(restaurant)){
                    orderMap.get(restaurant).add(restaurant.getItem(itemName));
                    break;
                }

                if(restaurant.tryReserveSlot()){
                    Item item = restaurant.getItem(itemName);
                    orderMap.computeIfAbsent(restaurant, r -> new ArrayList<>()).add(item);
                    break;
                }
            }
        }

        // check if all items are available to order
        int assignedItemsCount = orderMap.values()
                .stream()
                .mapToInt(List::size)
                .sum();
        if(assignedItemsCount < itemNames.size()){
            // release already booked slots
            for(Restaurant restaurant : orderMap.keySet()){
                restaurant.releaseSlot();
            }
            return null;
        }

        // order is created at this point of time
        return new Order(orderMap);
    }
}
