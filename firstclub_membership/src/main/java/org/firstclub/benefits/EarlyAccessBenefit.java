package org.firstclub.benefits;

public class EarlyAccessBenefit implements Benefit {

    @Override
    public BenefitType getType() {
        return BenefitType.EARLY_ACCESS;
    }

    @Override
    public String getDescription() {
        return "Early Access to Sales";
    }
}
