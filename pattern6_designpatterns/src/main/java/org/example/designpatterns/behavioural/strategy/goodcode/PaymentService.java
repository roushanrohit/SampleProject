package org.example.designpatterns.behavioural.strategy.goodcode;

/*
    1. Tomorrow if we want to introduce a new payment method, we will not
       have to make any changes to the payment service class.
    2. PaymentService class has single responsibilities -- follows SRP
    3. Easy to maintain code as more payment methods are added

    Strategy pattern involves creating an interface for strategies,
    allowing different concrete classes to implement that interface and provide various behaviors.
 */
public class PaymentService {

    public void processPayment(PaymentMethod paymentMethod){
        paymentMethod.pay();
    }
}
