package org.example.designpatterns.structural.adapter;

/*
    It is a structural design pattern that allows objects with
    incompatible interfaces to work together
 */
public class Client {

    public static void main(String[] args) {

        NotificationService emailService = new EmailNotificationService();
        emailService.send("customer@codingminutes.com","Order confirmation","Your order has been received");

        System.out.println("New Send Grid Adapter");

        SendGridService sendGridService = new SendGridService();
        NotificationService sendGridAdapter = new SendGridAdapter(sendGridService);
        sendGridAdapter.send("customer@codingminutes.com","Order confirmation","Your order has been received");

    }
}
