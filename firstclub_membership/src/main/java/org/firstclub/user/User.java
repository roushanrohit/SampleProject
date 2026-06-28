package org.firstclub.user;

import org.firstclub.membership.Membership;
import org.firstclub.utility.IdGenerator;

public class User {

    private long id;
    private String name;
    private Membership membership;
    private int orderCount;
    private double monthlyOrderValue;

    public User(String name) {
        this.id = IdGenerator.nextUserId();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public long getId() {
        return this.id;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getMonthlyOrderValue() {
        return monthlyOrderValue;
    }

    public void setMonthlyOrderValue(double monthlyOrderValue) {
        this.monthlyOrderValue = monthlyOrderValue;
    }
}
