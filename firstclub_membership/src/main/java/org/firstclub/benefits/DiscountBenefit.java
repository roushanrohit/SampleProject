package org.firstclub.benefits;

import java.util.List;

public class DiscountBenefit implements Benefit {

    private final int percentage;
    private final List<String> eligibleCategories;

    public DiscountBenefit(int percentage, List<String> eligibleCategories) {
        this.percentage = percentage;
        this.eligibleCategories = eligibleCategories;
    }

    @Override
    public BenefitType getType() {
        return BenefitType.DISCOUNT;
    }

    @Override
    public String getDescription() {
        return percentage + " % discount";
    }

    public List<String> getEligibleCategories() {
        return eligibleCategories;
    }

    public void addEligibleCategory(String category){
        eligibleCategories.add(category);
    }
}
