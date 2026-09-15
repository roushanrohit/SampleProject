package org.example.designpatterns.behavioural.mediator;

public class User {

    private String name;
    private ChatMediator chatMediator;

    public User(String name, ChatMediator chatMediator){
        this.name = name;
        this.chatMediator = chatMediator;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message){
        chatMediator.sendMessage(message, this);
    }

    public void receiveMessage(String msg, User sender){
        System.out.println("User " + name + " received message : " + msg + " from user " + sender.getName());
    }
}
