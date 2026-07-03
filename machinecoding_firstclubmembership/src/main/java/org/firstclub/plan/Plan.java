package org.firstclub.plan;

import org.firstclub.utility.IdGenerator;

import java.math.BigDecimal;

public class Plan {

    private long id;
    private String name;
    private BigDecimal price;
    private int durationInDays;

    public Plan(String name, BigDecimal price, int durationInDays) {
        this.id = IdGenerator.getPlanId();
        this.name = name;
        this.price = price;
        this.durationInDays = durationInDays;
        this.id = IdGenerator.getTierId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}
