package org.example.designpatterns.structural.decorator.goodcode;

public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizza) {
        super(pizza);
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
