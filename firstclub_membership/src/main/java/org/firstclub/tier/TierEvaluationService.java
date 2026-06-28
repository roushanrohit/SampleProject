package org.firstclub.tier;

import org.firstclub.user.User;

import java.util.Comparator;
import java.util.List;

public class TierEvaluationService {

    private final TierDAOService tierDAOService;

    public TierEvaluationService(TierDAOService tierDAOService) {
        this.tierDAOService = tierDAOService;
    }

    /*
        Returns the highest eligible tier for a user
     */
    public Tier evaluate(User user) {

        List<Tier> tiers = tierDAOService.findAll();
        tiers.sort(Comparator.comparingInt(Tier::getMinOrderCount).thenComparingDouble(Tier::getMinOrderValue).reversed());
        for (Tier tier : tiers) {
            if (tier.isEligible(user)) {
                return tier;
            }
        }

        throw new IllegalStateException("No eligible tier found.");
    }
}
