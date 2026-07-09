package org.pricingengine.exception;

public class DuplicateOfferException extends RuntimeException {
    public DuplicateOfferException(String offerId) {
        super("Offer already exists: " + offerId);
    }
}
