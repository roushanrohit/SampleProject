package org.example.designpatterns.behavioural.strategy.goodcode;

public class CreditCard implements PaymentMethod{
    @Override
    public void pay() {
        System.out.println("Making payment via Credit Card");
    }
}
