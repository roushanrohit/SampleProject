package org.firstclub.tier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TierRepository {

    private final ConcurrentMap<String, Tier> tierMap = new ConcurrentHashMap<>();

    public Optional<Tier> findByName(String tierName) {
        return Optional.ofNullable(tierMap.get(tierName.toUpperCase()));
    }

    public void save(Tier tier) throws IllegalArgumentException {

        /*
            If the key does not exist: Inserts the key-value pair. Returns null.
            If the key already exists: Does not overwrite the existing value. Returns the existing value.
         */
        Tier existing = tierMap.putIfAbsent(tier.getName().toUpperCase(), tier);

        if (existing != null) {
            throw new IllegalArgumentException("Tier already exists : " + tier.getName());
        }
    }

    public List<Tier> findAll() {
        return new ArrayList<>(tierMap.values());
    }

    public void delete(String tierName) {
        tierMap.remove(tierName.toUpperCase());
    }
}
