package org.pricingengine.offer;

import java.util.Set;

// Centralizes offer construction so adding a new offer type later (e.g. Buy 1, Get 1 Free)
// only means adding a factory method here plus a new Offer subclass.
public final class OfferFactory {

    private OfferFactory() {
    }

    public static Offer percentageOffer(String offerId, Set<String> productIds, int minQuantity,
                                         int maxGlobalUses, double discountPercentage) {
        return new PercentageDiscountOffer(offerId, productIds, minQuantity, maxGlobalUses, discountPercentage);
    }

    public static Offer flatOffer(String offerId, Set<String> productIds, int minQuantity,
                                   int maxGlobalUses, double discountPerUnit) {
        return new FlatDiscountOffer(offerId, productIds, minQuantity, maxGlobalUses, discountPerUnit);
    }
}
