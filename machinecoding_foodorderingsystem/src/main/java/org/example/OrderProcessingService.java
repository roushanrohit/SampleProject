package org.example;

import java.util.List;
import java.util.Map;

public class OrderProcessingService {

    public void processOrder(Order order){

        Map<Restaurant, List<Item>> orderedItems = order.getOrderedItems();
        for(Map.Entry<Restaurant, List<Item>> entry : orderedItems.entrySet()){

            Restaurant restaurant = entry.getKey();
            restaurant.prepare(entry.getValue());
            restaurant.releaseSlot();
        }
    }
}
