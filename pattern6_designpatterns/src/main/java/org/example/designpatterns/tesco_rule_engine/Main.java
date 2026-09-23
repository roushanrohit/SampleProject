package org.example.designpatterns.tesco_rule_engine;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<OrderItem> items = List.of(
                new OrderItem(1, "Paracetamol", 3),
                new OrderItem(2, "Analgesic", 3),
                new OrderItem(3, "Chocolate", 8),
                new OrderItem(4, "Paracetamol", 2)
        );

        RuleEngine ruleEngine = new RuleEngine();
        ruleEngine.addRule(new BulkBuyLimitRule(10));
        ruleEngine.addRule(new BulkBuyLimitCategoryRule(8, "Paracetamol"));
        // remove a rule
        ruleEngine.removeRule("BulkBuyLimitRule");

        System.out.println(ruleEngine.check(items));
    }
}
