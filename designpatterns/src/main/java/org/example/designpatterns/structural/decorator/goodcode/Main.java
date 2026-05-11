package org.example.designpatterns.structural.decorator.goodcode;

/*
    You need to add functionality to a class at runtime
    But subclassing would lead to class explosion
    Using the decorator pattern, we just need to have n decorator classes
    where n is the number of ingredients
 */
public class Main {

    public static void main(String[] args) {

        Pizza pizza = new BasicPizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new MushroomDecorator(pizza);
        pizza = new OliveDecorator(pizza);

        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());
    }
}
