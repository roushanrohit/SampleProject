package org.pricingengine.model.record;

import java.math.BigDecimal;

public record OfferQuote(String offerId, String description, BigDecimal discount) {}