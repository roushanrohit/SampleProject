package org.tesco_rule_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkBuyLimitRule implements Rule {

    private int limit;

    public BulkBuyLimitRule(int limit){
        this.limit = limit;
    }

    @Override
    public RuleResult check(List<OrderItem> orderItems) {

        // product id and quantity
        Map<Integer, Integer> quantityByProduct = new HashMap<>();
        for(OrderItem orderItem : orderItems){
            quantityByProduct.put(orderItem.getProductId(),
                    quantityByProduct.getOrDefault(orderItem.getProductId(), 0) + 1);
        }

        for(int quantity : quantityByProduct.values()){
            if(quantity > limit){
                return RuleResult.BREACHED;
            }
        }
        return RuleResult.MET;
    }
}
