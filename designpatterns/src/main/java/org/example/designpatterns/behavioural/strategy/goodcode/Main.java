package org.example.designpatterns.behavioural.strategy.goodcode;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();
        PaymentMethod creditCard = new CreditCard();
        PaymentMethod debitCard = new Debitcard();

        paymentService.processPayment(creditCard);
        paymentService.processPayment(debitCard);
    }
}
