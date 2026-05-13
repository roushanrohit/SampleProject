package org.example;

import java.util.List;
import java.util.Map;

public class Order {

    // Restaurant and list of items ordered from that restaurant
    private Map<Restaurant, List<Item>> orderedItems;

    private int orderId;

    public Order(Map<Restaurant, List<Item>> orderedItems,
                 int orderId) {
        this.orderedItems = orderedItems;
        this.orderId = orderId;
    }

    public Map<Restaurant, List<Item>> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<Restaurant, List<Item>> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", orderId=" + orderId +
                '}';
    }
}
