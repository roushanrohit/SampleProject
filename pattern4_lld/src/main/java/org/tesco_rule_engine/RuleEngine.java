package org.tesco_rule_engine;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    private List<Rule> ruleList;

    public RuleEngine(){
        this.ruleList = new ArrayList<>();
    }

    public void addRule(Rule rule){
        ruleList.add(rule);
    }

    public void removeRule(String ruleName) {
        ruleList.removeIf(rule -> rule.getClass().getSimpleName().equals(ruleName));
    }

    public boolean check(List<OrderItem> orderItems){
        for(Rule rule : ruleList){
            if(rule.check(orderItems) == RuleResult.BREACHED){
                return false;
            }
        }
        return true;
    }
}
