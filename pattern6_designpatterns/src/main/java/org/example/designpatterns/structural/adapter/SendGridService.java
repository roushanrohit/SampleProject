package org.example.designpatterns.structural.adapter;

public class SendGridService {

    public void sendEmail(String recipient, String title, String content){

        System.out.println("Sending email to: " + recipient);
        System.out.println("Email title: " + title);
        System.out.println("Email content: " + content);
    }
}
