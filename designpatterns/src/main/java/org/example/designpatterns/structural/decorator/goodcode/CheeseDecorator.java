package org.example.designpatterns.structural.decorator.goodcode;

public class CheeseDecorator extends PizzaDecorator {

    public CheeseDecorator(Pizza pizza) {

        /*
            Only one object that is of CheeseDecorator is created,
            parent class constructor is called to initialize
            the inherited fields first(parent's portion of the current object):
            protected static int noOfToppingsAdded = 0;
            protected Pizza decoratedPizza;

            This is why you can call super() even if the parent class is abstract.
            You cannot create an object of an abstract class, yet its constructor must run
            to initialize the fields it provides to its subclasses.
         */
        super(pizza);
        noOfToppingsAdded++;
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ",cheese";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 5.0;
    }
}
