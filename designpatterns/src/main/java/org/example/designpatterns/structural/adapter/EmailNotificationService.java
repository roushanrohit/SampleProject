package org.example.designpatterns.structural.adapter;

// Legacy code
public class EmailNotificationService implements NotificationService {

    public void send(String to, String subject, String body){

        System.out.println("Sending email to: " + to);
        System.out.println("Email subject: " + subject);
        System.out.println("Email body: " + body);
    }
}
