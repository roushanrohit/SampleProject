package org.firstclub.tier;

import org.firstclub.benefits.Benefit;
import org.firstclub.benefits.BenefitType;
import org.firstclub.user.User;

import java.util.ArrayList;
import java.util.List;

public class Tier {

    private long id;
    private String name;
    private List<Benefit> benefits;
    private int minOrderCount;
    private double minOrderValue;

    public Tier(String name,
                List<Benefit> benefits,
                int minOrderCount,
                double minOrderValue) {

        this.name = name;
        this.benefits = new ArrayList<>(benefits);
        this.minOrderCount = minOrderCount;
        this.minOrderValue = minOrderValue;
    }

    public String getName() {
        return name;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void addBenefit(Benefit benefit) {

        boolean exists = benefits.stream()
                .anyMatch(b -> b.getType() == benefit.getType());

        if (exists) {
            throw new IllegalArgumentException(
                    "Benefit already exists : " + benefit.getType());
        }

        benefits.add(benefit);
    }

    public void removeBenefit(BenefitType type) {

        benefits.removeIf(
                benefit -> benefit.getType() == type);
    }

    public int getMinOrderCount() {
        return minOrderCount;
    }

    public void setMinOrderCount(int minOrderCount) {
        this.minOrderCount = minOrderCount;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    @Override
    public String toString() {

        return "Tier{" +
                "name='" + name + '\'' +
                ", minOrderCount=" + minOrderCount +
                ", minOrderValue=" + minOrderValue +
                ", benefits=" + benefits +
                '}';
    }

    public long getId() {
        return id;
    }

    public boolean isEligible(User user) {
        return user.getOrderCount() >= minOrderCount && user.getMonthlyOrderValue() >= minOrderValue;
    }
}
