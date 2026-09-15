package org.pricingengine.offer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class PercentageDiscountOffer extends Offer {

    private final BigDecimal discountPercentage;

    public PercentageDiscountOffer(String offerId, Set<String> productIds, int minQuantity,
                                    int maxGlobalUses, double discountPercentage) {
        super(offerId, productIds, minQuantity, maxGlobalUses);
        if (discountPercentage <= 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("discountPercentage must be in (0, 100]: " + discountPercentage);
        }
        this.discountPercentage = BigDecimal.valueOf(discountPercentage);
    }

    @Override
    public BigDecimal calculateDiscount(int quantity, BigDecimal lineBaseTotal) {
        return lineBaseTotal.multiply(discountPercentage)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        return discountPercentage.stripTrailingZeros().toPlainString() + "%";
    }
}
