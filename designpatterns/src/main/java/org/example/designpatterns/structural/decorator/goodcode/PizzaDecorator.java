package org.example.designpatterns.structural.decorator.goodcode;

/*
    Why we need a abstract class in between -- why can't we create CheeseDecorator,
    MushroomDecorator and OliveDecorator classes directly?

    1. Sometimes decorators only override one method, like let's say we only want to
       change the description and keep the cost same.
       In our concrete decorator classes, we can only override the getCost() method.

    2. Suppose later we want all decorators to support some additional methods like
       isPremium(), rather than adding this code in all subclasses, we can add it in
       the PizzaDecorator abstract class.
 */
public abstract class PizzaDecorator implements Pizza {

    /*
        A static variable in an abstract class is shared among all its subclasses and their instances.
        We can access it through PizzaDecorator class name or any of its subclasses class names.
     */
    protected static int noOfToppingsAdded = 0;
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza pizza){
        decoratedPizza = pizza;
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }
}
