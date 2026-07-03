package org.firstclub.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserMembershipRepository {

    private final ConcurrentMap<Long, UserMembership> userMembershipMap =
            new ConcurrentHashMap<>();

    public UserMembership save(UserMembership userMembership) throws IllegalArgumentException {

        /*
            If the key does not exist: Inserts the key-value pair. Returns null.
            If the key already exists: Does not overwrite the existing value. Returns the existing value.
         */
        UserMembership existing = userMembershipMap.putIfAbsent(userMembership.getUserId(), userMembership);

        if (existing != null) {
            throw new IllegalArgumentException("User already belongs to a membership.");
        }

        return userMembership;
    }

    public Optional<UserMembership> findByUserId(long userId) {

        return Optional.ofNullable(userMembershipMap.get(userId));
    }

    public List<UserMembership> findByMembershipId(long membershipId) {

        List<UserMembership> result = new ArrayList<>();

        for (UserMembership userMembership : userMembershipMap.values()) {

            if (userMembership.getMembershipId() == membershipId) {
                result.add(userMembership);
            }
        }

        return result;
    }

    public List<UserMembership> findAll() {

        return new ArrayList<>(userMembershipMap.values());
    }

    public void delete(long userId) {

        userMembershipMap.remove(userId);
    }
}
