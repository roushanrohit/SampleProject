package org.tesco_rule_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkBuyLimitCategoryRule implements Rule {

    private int limit;
    private String category;

    public BulkBuyLimitCategoryRule(int limit, String category){
        this.limit = limit;
        this.category = category;
    }

    @Override
    public RuleResult check(List<OrderItem> orderItems) {

        // category and quantity
        Map<String, Integer> quantityByCategory = new HashMap<>();
        for(OrderItem orderItem : orderItems){
            quantityByCategory.put(orderItem.getCategory(),
                    quantityByCategory.getOrDefault(orderItem.getCategory(), 0) + 1);
        }

        for(int quantity : quantityByCategory.values()){
            if(quantity > limit){
                return RuleResult.BREACHED;
            }
        }
        return RuleResult.MET;
    }
}
