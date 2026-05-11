package org.example.designpatterns.structural.decorator.badcode;

public class CheeseOlivePizza extends CheesePizza {

    @Override
    public String getDescription() {
        return super.getDescription() + ",olives";
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }
}
