package org.gitvcs.model;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/*
    Each branch owns its own lock, so commits to different branches never contend with
    each other. The lock is acquired before the HEAD is read for a commit/merge, which
    is what keeps concurrent writers from racing on the same branch.
 */
public class Branch {

    private final String name;
    private final AtomicReference<Commit> head;
    private final ReentrantLock lock = new ReentrantLock();

    public Branch(String name, Commit head) {
        this.name = name;
        this.head = new AtomicReference<>(head);
    }

    public String getName() {
        return name;
    }

    public AtomicReference<Commit> getHead() {
        return head;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
