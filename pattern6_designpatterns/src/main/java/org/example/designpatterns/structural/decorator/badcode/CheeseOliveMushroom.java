package org.example.designpatterns.structural.decorator.badcode;

public class CheeseOliveMushroom extends CheeseOlivePizza {

    @Override
    public String getDescription() {
        return super.getDescription() + ",mushroom";
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }
}
