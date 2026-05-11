package org.example.designpatterns.behavioural.strategy.goodcode;

public class Debitcard implements PaymentMethod{
    @Override
    public void pay() {
        System.out.println("Making payment via Debit Card");
    }
}
