package org.firstclub.utility;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {

    private static final AtomicLong USER_ID = new AtomicLong(1);
    private static final AtomicLong TIER_ID = new AtomicLong(1);
    private static final AtomicLong PLAN_ID = new AtomicLong(1);
    private static final AtomicLong MEMBERSHIP_ID = new AtomicLong(1);

    private IdGenerator() {}

    public static long nextUserId() {
        return USER_ID.getAndIncrement();
    }

    public static long getTierId() {
        return TIER_ID.getAndIncrement();
    }

    public static long getPlanId() {
        return PLAN_ID.getAndIncrement();
    }

    public static long getMembershipId() {
        return MEMBERSHIP_ID.getAndIncrement();
    }
}
