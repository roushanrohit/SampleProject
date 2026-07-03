package org.firstclub.benefits;

public class PrioritySupportBenefit implements Benefit {

    @Override
    public BenefitType getType() {
        return BenefitType.PRIORITY_SUPPORT;
    }

    @Override
    public String getDescription() {
        return "Priority Customer Service and Support";
    }
}
