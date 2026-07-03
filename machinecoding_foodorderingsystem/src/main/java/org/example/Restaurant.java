package org.example;

import java.util.*;

/*
    createOrder() should NOT belong to Restaurant.
    A Restaurant represents:
    menu
    pricing
    capacity
    restaurant state

    It should NOT know:
    all restaurants in the system
    pricing algorithms
    fallback logic
    multi-restaurant orders

    If you put createOrder() inside Restaurant class, it will violate SRP
 */
public class Restaurant {

    private final String name;

    private final Map<String, Item> itemMap;

    // Maximum active orders restaurant can process
    private final int processingCapacity;

    // Current active orders
    private int currentOrders;

    public Restaurant(String name, int processingCapacity) {
        this.name = name;
        this.processingCapacity = processingCapacity;
        this.currentOrders = 0;
        this.itemMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        itemMap.put(item.getName(), item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(itemMap.values());
    }

    public void updatePrice(Item item) throws ItemNotFoundException {

        Item existingItem = itemMap.get(item.getName());
        if (existingItem == null) {
            throw new ItemNotFoundException();
        }
        existingItem.setPrice(item.getPrice());
    }

    public Item getItem(String itemName) {
        return itemMap.get(itemName);
    }

    public boolean canProcess() {
        return currentOrders < processingCapacity;
    }

    public synchronized boolean tryReserveSlot() {
        if (!canProcess()) return false;
        currentOrders++;
        return true;
    }

    public synchronized void releaseSlot() {
        if (currentOrders > 0) {
            currentOrders--;
        }
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", processingCapacity=" + processingCapacity +
                ", currentOrders=" + currentOrders +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Restaurant that)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void prepare(List<Item> items) {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
