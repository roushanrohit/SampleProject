package org.firstclub.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PlanRepository {

    private final ConcurrentMap<String, Plan> planMap = new ConcurrentHashMap<>();

    public Optional<Plan> findByName(String planName) {
        return Optional.ofNullable(planMap.get(planName.toUpperCase()));
    }

    public void save(Plan plan) throws IllegalArgumentException {

        /*
            If the key does not exist: Inserts the key-value pair. Returns null.
            If the key already exists: Does not overwrite the existing value. Returns the existing value.
         */
        Plan existing = planMap.putIfAbsent(plan.getName().toUpperCase(), plan);

        if (existing != null) {
            throw new IllegalArgumentException("Plan already exists : " + plan.getName());
        }
    }

    public List<Plan> findAll() {
        return new ArrayList<>(planMap.values());
    }

    public void delete(String tierName) {
        planMap.remove(tierName.toUpperCase());
    }
}
