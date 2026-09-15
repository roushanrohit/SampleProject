package org.pricingengine.model.record;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public record CartLineResult(String productId, int quantity, BigDecimal baseTotal, List<OfferQuote> validOffers,
                             String appliedOfferId, BigDecimal discount, BigDecimal finalPrice) {

    public CartLineResult(String productId, int quantity, BigDecimal baseTotal, List<OfferQuote> validOffers,
                          String appliedOfferId, BigDecimal discount, BigDecimal finalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.baseTotal = baseTotal;
        this.validOffers = Collections.unmodifiableList(validOffers);
        this.appliedOfferId = appliedOfferId;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }
}
