package org.pricingengine.exception;

public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String productId) {
        super("Product already exists: " + productId);
    }
}
