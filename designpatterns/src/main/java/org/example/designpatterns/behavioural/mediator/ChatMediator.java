package org.example.designpatterns.behavioural.mediator;

public interface ChatMediator {

    void sendMessage(String message, User sender);

    void addUser(User user);
}
