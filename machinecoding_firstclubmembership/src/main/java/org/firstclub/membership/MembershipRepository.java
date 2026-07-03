package org.firstclub.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MembershipRepository {

    private final ConcurrentMap<Long, Membership> membershipMap = new ConcurrentHashMap<>();

    public Membership save(Membership membership) {

        membershipMap.put(membership.getId(), membership);
        return membership;
    }

    public Optional<Membership> findById(long membershipId) {

        return Optional.ofNullable(membershipMap.get(membershipId));
    }

    public List<Membership> findAll() {

        return new ArrayList<>(membershipMap.values());
    }

    public void delete(long membershipId) {

        membershipMap.remove(membershipId);
    }
}
