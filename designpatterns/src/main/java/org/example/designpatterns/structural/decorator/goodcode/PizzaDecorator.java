package org.example.designpatterns.structural.decorator.goodcode;

public abstract class PizzaDecorator implements Pizza {

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
