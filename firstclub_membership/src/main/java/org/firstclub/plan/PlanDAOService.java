package org.firstclub.plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PlanDAOService {

    private static final PlanRepository planRepository = new PlanRepository();

    // create some default plans
    static {

        // MONTHLY
        planRepository.save(new Plan("MONTHLY", new BigDecimal("299.0"), 30));

        // QUARTERLY
        planRepository.save(new Plan("QUARTERLY", new BigDecimal("599.0"), 90));

        // YEARLY
        planRepository.save(new Plan("YEARLY", new BigDecimal("1299.0"), 360));
    }

    public Plan getOrCreatePlan(String name, BigDecimal price, int durationInDays) {

        Optional<Plan> optional = planRepository.findByName(name);
        if(optional.isPresent()) return optional.get();
        else {
            Plan plan = new Plan(name, price, durationInDays);
            planRepository.save(plan);
            return plan;
        }
    }

    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    public void delete(String planName) {
        planRepository.delete(planName);
    }

    public void save(Plan plan) throws IllegalArgumentException {
        planRepository.save(plan);
    }

    public Optional<Plan> findByName(String planName) {
        return planRepository.findByName(planName);
    }
}
