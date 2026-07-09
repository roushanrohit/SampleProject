package org.pricingengine.strategy;

import org.pricingengine.offer.Offer;

import java.math.BigDecimal;
import java.util.List;

// Picks the single offer that yields the highest monetary discount for the line item.
public class BestSingleOfferStrategy implements OfferSelectionStrategy {

    @Override
    public Offer select(List<Offer> eligibleOffers, int quantity, BigDecimal lineBaseTotal) {
        Offer best = null;
        BigDecimal bestDiscount = null;
        for (Offer offer : eligibleOffers) {
            BigDecimal discount = offer.calculateDiscount(quantity, lineBaseTotal);
            if (bestDiscount == null || discount.compareTo(bestDiscount) > 0) {
                bestDiscount = discount;
                best = offer;
            }
        }
        return best;
    }
}
