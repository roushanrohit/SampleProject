package org.example.designpatterns.structural.decorator.badcode;

/*
    You need to add functionality to a class at runtime
    But subclassing would lead to class explosion
    There can be n number of pizza combination (for 10 ingredients there can be 1024 combinations)
    Each new combination requires a code change
 */
public class Main {

    public static void main(String[] args) {

        Pizza pizza = new CheeseOlivePizza();
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());

    }
}
