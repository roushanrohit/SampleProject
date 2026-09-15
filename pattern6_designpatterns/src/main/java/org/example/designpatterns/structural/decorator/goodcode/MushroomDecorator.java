package org.example.designpatterns.structural.decorator.goodcode;

public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizza) {

        /*
            Only one object that is of MushroomDecorator is created,
            parent class constructor is called to initialize
            the inherited fields first(parent's portion of the current object): decoratedPizza

            This is why you can call super() even if the parent class is abstract.
            You cannot create an object of an abstract class, yet its constructor must run
            to initialize the fields it provides to its subclasses.
         */
        super(pizza);
        noOfToppingsAdded++;
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ",mushroom";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 5.0;
    }
}
