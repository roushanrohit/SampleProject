package org.firstclub.membership;

import org.firstclub.plan.Plan;
import org.firstclub.tier.Tier;
import org.firstclub.utility.IdGenerator;

import java.time.LocalDate;

public class Membership {

    private long id;
    private String planName;
    private String tierName;
    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipStatus membershipStatus;

    public Membership(Plan plan, Tier tier, LocalDate startDate) {
        this.id = IdGenerator.getMembershipId();
        this.tierName = tier.getName();
        this.startDate = startDate;
        this.membershipStatus = MembershipStatus.ACTIVE;
        this.planName = plan.getName();
        this.endDate = startDate.plusDays(plan.getDurationInDays());
    }

    public void cancel() throws IllegalStateException {

        if (membershipStatus == MembershipStatus.CANCELLED) {
            throw new IllegalStateException("Membership already cancelled.");
        }
        membershipStatus = MembershipStatus.CANCELLED;
    }

    public String getPlanName() {
        return planName;
    }

    public void changePlan(Plan plan) throws IllegalArgumentException {
        this.planName = plan.getName();
        this.endDate = startDate.plusDays(plan.getDurationInDays());
    }

    public String getTierName() {
        return tierName;
    }

    public void changeTier(Tier tier) {
        this.tierName = tier.getName();
    }

    public long getId() {
        return id;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }
}
