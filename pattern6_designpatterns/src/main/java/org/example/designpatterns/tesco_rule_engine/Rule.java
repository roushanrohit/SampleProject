package org.example.designpatterns.tesco_rule_engine;

import java.util.List;

public interface Rule {

    RuleResult check(List<OrderItem> orderItems);
}
