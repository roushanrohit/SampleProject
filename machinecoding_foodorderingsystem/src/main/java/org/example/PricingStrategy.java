package org.example;

import java.util.List;

public interface PricingStrategy {

    public Order createOrder(List<Restaurant> restaurants, List<String> itemNames);
}
