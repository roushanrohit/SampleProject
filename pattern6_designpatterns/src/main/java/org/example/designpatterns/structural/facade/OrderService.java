package org.example.designpatterns.structural.facade;

/*
    Microservice for order processing
 */
public class OrderService {

    public String processOrder(String orderId){

        // simulate fetching order details
        return "Order processed successfully : " + orderId;
    }
}
