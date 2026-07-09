package org.pricingengine.model;

import org.pricingengine.model.enums.CartStatus;
import org.pricingengine.model.record.CartEvaluation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {

    private final String cartId;
    private final Map<String, Integer> items = new LinkedHashMap<>();
    private final AtomicReference<CartStatus> status = new AtomicReference<>(CartStatus.OPEN);
    private volatile CartEvaluation checkoutSnapshot;

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public synchronized void addItem(String productId, int quantity) {
        items.merge(productId, quantity, Integer::sum);
    }

    public synchronized Map<String, Integer> snapshotItems() {
        return new LinkedHashMap<>(items);
    }

    public boolean isCheckedOut() {
        return status.get() == CartStatus.CHECKED_OUT;
    }

    public boolean markCheckedOut() {
        return status.compareAndSet(CartStatus.OPEN, CartStatus.CHECKED_OUT);
    }

    public CartEvaluation getCheckoutSnapshot() {
        return checkoutSnapshot;
    }

    public void setCheckoutSnapshot(CartEvaluation checkoutSnapshot) {
        this.checkoutSnapshot = checkoutSnapshot;
    }
}
