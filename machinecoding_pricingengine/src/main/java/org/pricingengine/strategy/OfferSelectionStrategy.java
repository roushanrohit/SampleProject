package org.pricingengine.strategy;

import org.pricingengine.offer.Offer;

import java.math.BigDecimal;
import java.util.List;

// Pluggable policy for choosing which of a line item's eligible offers to apply.
public interface OfferSelectionStrategy {
    Offer select(List<Offer> eligibleOffers, int quantity, BigDecimal lineBaseTotal);
}
