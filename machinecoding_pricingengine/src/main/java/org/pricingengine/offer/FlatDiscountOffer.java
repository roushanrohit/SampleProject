package org.pricingengine.offer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class FlatDiscountOffer extends Offer {

    private final BigDecimal discountPerUnit;

    public FlatDiscountOffer(String offerId, Set<String> productIds, int minQuantity,
                              int maxGlobalUses, double discountPerUnit) {
        super(offerId, productIds, minQuantity, maxGlobalUses);
        if (discountPerUnit <= 0) {
            throw new IllegalArgumentException("discountPerUnit must be > 0: " + discountPerUnit);
        }
        this.discountPerUnit = BigDecimal.valueOf(discountPerUnit);
    }

    @Override
    public BigDecimal calculateDiscount(int quantity, BigDecimal lineBaseTotal) {
        BigDecimal discount = discountPerUnit.multiply(BigDecimal.valueOf(quantity));
        return discount.min(lineBaseTotal).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        return "Flat " + discountPerUnit.stripTrailingZeros().toPlainString() + "/unit";
    }
}
