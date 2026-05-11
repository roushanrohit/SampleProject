package org.example.designpatterns.structural.decorator.goodcode;

public class OliveDecorator extends PizzaDecorator {

    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ",olives";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 5.0;
    }
}
