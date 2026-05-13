package org.example;

import java.util.*;

public class LowestPriceStrategy implements PricingStrategy {

    @Override
    public Order createOrder(List<Restaurant> restaurants, List<String> itemNames) {

        Map<Restaurant, List<Item>> orderMap = new HashMap<>();

        for(String itemName : itemNames){

            Restaurant cheapestRestaurant = null;
            Item cheapestItem = null;

            for(Restaurant restaurant : restaurants){

                Item item = restaurant.getItem(itemName);

                if(item == null){
                    continue;
                }

                if(cheapestItem == null ||
                        item.getPrice() < cheapestItem.getPrice()){

                    cheapestItem = item;
                    cheapestRestaurant = restaurant;
                }
            }

            if(cheapestItem == null){
                throw new RuntimeException(
                        "Item not available: " + itemName
                );
            }

            orderMap
                    .computeIfAbsent(
                            cheapestRestaurant,
                            r -> new ArrayList<>()
                    )
                    .add(cheapestItem);
        }

        return new Order(orderMap, generateOrderId());
    }

    private int generateOrderId(){
        return new Random().nextInt(100000);
    }
}
