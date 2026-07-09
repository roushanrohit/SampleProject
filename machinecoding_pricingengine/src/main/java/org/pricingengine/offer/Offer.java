package org.pricingengine.offer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Offer {

    private final String offerId;
    private final Set<String> productIds;
    private final int minQuantity;
    private final AtomicInteger remainingUses;

    protected Offer(String offerId, Set<String> productIds, int minQuantity, int maxGlobalUses) {
        if (offerId == null || offerId.isBlank()) {
            throw new IllegalArgumentException("offerId must not be blank");
        }
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("An offer must be associated with at least one product");
        }
        if (minQuantity < 1) {
            throw new IllegalArgumentException("minQuantity must be >= 1");
        }
        if (maxGlobalUses < 1) {
            throw new IllegalArgumentException("maxGlobalUses must be >= 1");
        }
        this.offerId = offerId;
        this.productIds = Collections.unmodifiableSet(productIds);
        this.minQuantity = minQuantity;
        this.remainingUses = new AtomicInteger(maxGlobalUses);
    }

    public String getOfferId() {
        return offerId;
    }

    public Set<String> getProductIds() {
        return productIds;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getRemainingUses() {
        return remainingUses.get();
    }

    public boolean isEligible(int quantity) {
        return quantity >= minQuantity && remainingUses.get() > 0;
    }

    /* Get current but decrement remaining uses only if no other thread updates it in between
       If we simply check the value and then decrement, two threads can read a value of 1 at the exact same time.
       Both see that it is greater than 0, both decrement it, and the count drops to -1
     */
    public boolean tryReserveUse() {
        int current;
        do {
            current = remainingUses.get();
            if (current <= 0) {
                return false;
            }
        } while (!remainingUses.compareAndSet(current, current - 1));
        return true;
    }

    // abstract method implementation in concrte classes FlatDiscountOffer and PercentageDiscountOffer
    public abstract BigDecimal calculateDiscount(int quantity, BigDecimal lineBaseTotal);

    // abstract method implementation in concrte classes FlatDiscountOffer and PercentageDiscountOffer
    public abstract String getDescription();
}
