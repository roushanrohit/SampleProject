package org.pricingengine.exception;

public class DuplicateCartException extends RuntimeException {
    public DuplicateCartException(String cartId) {
        super("Cart already exists: " + cartId);
    }
}
