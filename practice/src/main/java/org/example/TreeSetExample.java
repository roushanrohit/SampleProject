package org.example;

import java.util.*;

/*
    Maintain the top K entities dynamically as their scores change.
 */
public class TreeSetExample {

    static class User {
        int senderId;
        int amount;
        User(int senderId, int amount) {
            this.senderId = senderId;
            this.amount = amount;
        }
        public int getAmount() {
            return amount;
        }
        public int getSenderId() {
            return senderId;
        }
    }

    Map<Integer, User> usersMap = new HashMap<>();
    TreeSet<User> top100 = new TreeSet<>(Comparator.comparingInt(User::getAmount)
            .thenComparingInt(User::getSenderId));

    public void makePayment(int senderId, int amount) {
        User user = usersMap.get(senderId);
        if (user == null) {
            user = new User(senderId, amount);
            usersMap.put(senderId, user);
        } else {
            top100.remove(user); // O(logK) where K is the number of elements in the treeSet
            user.amount += amount;
        }
        top100.add(user); // O(logK)
        if (top100.size() > 100) {
            top100.pollFirst(); // O(logK)
        }
        boolean eligibleForReward = top100.contains(user); // O(logK)
        System.out.println("User " + senderId + " eligible = " + eligibleForReward);
    }

    PriorityQueue<User> top100PQ = new PriorityQueue<>(Comparator.comparingInt(User::getAmount)
            .thenComparingInt(User::getSenderId));

    public void makePaymentPQ(int senderId, int amount) {
        User user = usersMap.get(senderId);
        if (user == null) {
            user = new User(senderId, amount);
            usersMap.put(senderId, user);
        } else {
            top100PQ.remove(user); // O(K) where K is the number of elements in the PQ
            user.amount += amount;
        }
        top100PQ.offer(user); // O(logK)
        if (top100PQ.size() > 100) {
            top100PQ.poll(); // O(logK)
        }
        boolean eligibleForReward = top100.contains(user); // O(K) where K is the number of elements in the PQ
        System.out.println("User " + senderId + " eligible = " + eligibleForReward);
    }
}
