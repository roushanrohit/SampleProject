package org.example.designpatterns.structural.decorator.goodcode;

/*
    You need to add functionality to a class at runtime
    But subclassing would lead to class explosion
    Using the decorator pattern, we just need to have n decorator classes
    where n is the number of ingredients

    +----------------------+
    |   OliveDecorator     |
    |----------------------|
    | decoratedPizza ------|-------------------+
    +----------------------+                   |
                                               v
                                  +----------------------+
                                  | MushroomDecorator    |
                                  |----------------------|
                                  | decoratedPizza ------|-------------+
                                  +----------------------+             |
                                                                       v
                                                            +----------------------+
                                                            |  CheeseDecorator     |
                                                            |----------------------|
                                                            | decoratedPizza ------|------+
                                                            +----------------------+      |
                                                                                           v
                                                                                +------------------+
                                                                                |   BasicPizza     |
                                                                                |------------------|
                                                                                | "Basic Pizza"    |
                                                                                | cost: 5          |
                                                                                +------------------+

    pizza ─────────────► OliveDecorator
 */
public class Main {

    public static void main(String[] args) {

        Pizza pizza = new BasicPizza();
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());

        pizza = new CheeseDecorator(pizza);
        System.out.println("Number of toppings added: " + PizzaDecorator.noOfToppingsAdded);
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());

        pizza = new MushroomDecorator(pizza);
        System.out.println("Number of toppings added: " + PizzaDecorator.noOfToppingsAdded);
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());

        pizza = new OliveDecorator(pizza);
        System.out.println("Number of toppings added: " + PizzaDecorator.noOfToppingsAdded);
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());
    }
}
