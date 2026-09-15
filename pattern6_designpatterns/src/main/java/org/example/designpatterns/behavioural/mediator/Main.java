package org.example.designpatterns.behavioural.mediator;

/*
    Let's say we are building a chat application where each user can send message to every other user.
    Now as the number of users increase, it becomes a complex web of dependencies, also every user
    has to know about every other user.

    Mediator Pattern comes to the rescue.
    Scenario: In a chat system, multiple participants communicate through a central chat mediator,
              reducing the need for each participant to be aware of others.
              Mediator: chat server
              Colleagues: chat participants send messages to the mediator which then distributes them to others.
    1. Users only interact with the mediator -- loose coupling
    2. SRP - sending message logic is handled by the mediator, users can focus on their own logic
    3. Centralized control - changes to communication rules can be done in the mediator without affecting the users.

    Use cases:
    1. Air Traffic Control -- Airplanes interact with the central control tower instead of communicating with each other.
    2. GUI Component Coordination -- A dropdown change may trigger updates to text fields, buttons etc, a mediator
       can handle this interaction logic instead of having the components know about each other directly.
 */
public class Main {

    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();

        User rahul = new User("Rahul", chatRoom);
        User amit = new User("Amit", chatRoom);
        User neha = new User("Neha", chatRoom);

        chatRoom.addUser(rahul);
        chatRoom.addUser(amit);
        chatRoom.addUser(neha);

        amit.sendMessage("Hello");
    }
}
