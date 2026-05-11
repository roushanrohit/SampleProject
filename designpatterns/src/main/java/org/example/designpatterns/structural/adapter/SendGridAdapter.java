package org.example.designpatterns.structural.adapter;

public class SendGridAdapter implements NotificationService {

    private SendGridService sendGridService;

    public SendGridAdapter(SendGridService sendGridService){
        this.sendGridService = sendGridService;
    }

    @Override
    public void send(String to, String subject, String body) {
        // convert parameter and function calls to send grid's method
        sendGridService.sendEmail(to, subject, body);
    }
}
