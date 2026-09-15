package org.pricingengine.model;

import java.math.BigDecimal;

public class Product {

    private final String productId;
    private final BigDecimal basePrice;

    public Product(String productId, BigDecimal basePrice) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("productId must not be blank");
        }
        if (basePrice == null || basePrice.signum() <= 0) {
            throw new IllegalArgumentException("basePrice must be > 0");
        }
        this.productId = productId;
        this.basePrice = basePrice;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
