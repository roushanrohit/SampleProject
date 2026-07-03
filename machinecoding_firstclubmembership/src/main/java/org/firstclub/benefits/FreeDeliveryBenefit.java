package org.firstclub.benefits;

public class FreeDeliveryBenefit implements Benefit {

    @Override
    public BenefitType getType() {
        return BenefitType.FREE_DELIVERY;
    }

    @Override
    public String getDescription() {
        return "Free Delivery";
    }
}
