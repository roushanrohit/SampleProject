package org.example;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Order {

    // Restaurant and list of items ordered from that restaurant
    // Every order can span across multiple restaurants
    private final Map<Restaurant, List<Item>> orderedItems;

    private final int orderId;

    public Order(Map<Restaurant, List<Item>> orderedItems) {
        this.orderedItems = orderedItems;
        this.orderId = generateOrderId();
    }

    public Map<Restaurant, List<Item>> getOrderedItems() {
        return orderedItems;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", orderId=" + orderId +
                '}';
    }

    private int generateOrderId(){
        return new Random().nextInt(100000);
    }
}
