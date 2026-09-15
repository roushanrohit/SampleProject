package org.pricingengine.model.record;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public record CartEvaluation(String cartId, List<CartLineResult> lines, BigDecimal totalBase, BigDecimal totalDiscount,
                             BigDecimal finalAmount) {

    public CartEvaluation(String cartId, List<CartLineResult> lines, BigDecimal totalBase,
                          BigDecimal totalDiscount, BigDecimal finalAmount) {
        this.cartId = cartId;
        this.lines = Collections.unmodifiableList(lines);
        this.totalBase = totalBase;
        this.totalDiscount = totalDiscount;
        this.finalAmount = finalAmount;
    }
}
