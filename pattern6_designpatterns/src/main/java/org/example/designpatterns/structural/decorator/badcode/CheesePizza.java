package org.example.designpatterns.structural.decorator.badcode;

public class CheesePizza extends BasicPizza {

    @Override
    public String getDescription() {
        return super.getDescription() + ",cheese";
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }
}
