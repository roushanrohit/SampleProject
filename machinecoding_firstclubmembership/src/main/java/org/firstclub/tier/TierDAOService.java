package org.firstclub.tier;

import org.firstclub.benefits.Benefit;
import org.firstclub.benefits.DiscountBenefit;
import org.firstclub.benefits.FreeDeliveryBenefit;

import java.util.List;
import java.util.Optional;

public class TierDAOService {

    private static final TierRepository tierRepository = new TierRepository();

    // create some default tiers
    static {

        // SILVER
        tierRepository.save(new Tier("SILVER", List.of(new FreeDeliveryBenefit()),0,0));

        // GOLD
        tierRepository.save(new Tier("GOLD", List.of(new FreeDeliveryBenefit(),
                new DiscountBenefit(10, List.of("ALL"))), 50, 50000));

        // PLATINUM
        tierRepository.save(new Tier("PLATINUM", List.of(new FreeDeliveryBenefit(),
                new DiscountBenefit(20, List.of("ALL"))),100,100000));
    }

    public static Tier getOrCreateTier(String name, List<Benefit> benefits, int minOrderCount,
                                       int minOrderValue) {

        Optional<Tier> optional = tierRepository.findByName(name);
        if(optional.isPresent()) return optional.get();
        else {
            Tier tier = new Tier(name, benefits, minOrderCount, minOrderValue);
            tierRepository.save(tier);
            return tier;
        }
    }

    public List<Tier> findAll() {
        return tierRepository.findAll();
    }

    public void delete(String tierName) {
        tierRepository.delete(tierName);
    }

    public void save(Tier tier) throws IllegalArgumentException {
        tierRepository.save(tier);
    }

    public Optional<Tier> findByName(String tierName) {
        return tierRepository.findByName(tierName);
    }
}
