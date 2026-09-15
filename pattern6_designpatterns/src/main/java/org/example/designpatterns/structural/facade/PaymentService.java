package org.example.designpatterns.structural.facade;

/*
    Microservice for payment processing
 */
public class PaymentService {

    public String processPayment(String userId, String orderId){

        // simulate payment processing
        return "Payment processed for user: " + userId + " and order: " + orderId;
    }
}
