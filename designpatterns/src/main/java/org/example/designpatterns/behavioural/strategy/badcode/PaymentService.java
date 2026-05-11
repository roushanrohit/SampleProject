package org.example.designpatterns.behavioural.strategy.badcode;

/*
    1. Tomorrow if we want to introduce a new payment method, this will
       mean making changes in the existing tested code.
    2. PaymentService class has multiple responsibilities -- violates SRP
    3. Hard to maintain code as more payment methods are added

    With Strategy pattern, the logic for each payment type is
    encapsulated in separate strategy classes and the payment service
    delegates the task of payment processing to one of these classes at runtime.
 */
public class PaymentService {

    public void processPayment(String paymentMethod){

        if(paymentMethod.equals("DebitCard")){

            // debit card specific logic
            System.out.println("Making payment via Debit card");
        } else if (paymentMethod.equals("CreditCard")){

            // credit card specific logic
            System.out.println("Making payment via Credit card");
        } else {
            System.out.println("Unsupported Payment Method");
        }
    }
}
