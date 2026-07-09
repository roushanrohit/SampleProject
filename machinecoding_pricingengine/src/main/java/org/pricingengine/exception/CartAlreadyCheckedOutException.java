package org.pricingengine.exception;

public class CartAlreadyCheckedOutException extends RuntimeException {
    public CartAlreadyCheckedOutException(String cartId) {
        super("Cart already checked out: " + cartId);
    }
}
