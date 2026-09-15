package org.pricingengine.exception;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String offerId) {
        super("Offer not found: " + offerId);
    }
}
