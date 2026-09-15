package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {

    public static void main(String[] args) {

        List<Transaction> txns = new ArrayList<>();
        txns.add(new Transaction("ABC123", 100L, 350L));
        txns.add(new Transaction("ABC456", 200L, 400L));
        txns.add(new Transaction("ABC789", 200L, 550L));
        txns.add(new Transaction("DEF123", 300L, 500L));

        // sort the list by timestamp ascending and for equal timestamps, sort it descending by amount
        txns.sort(Comparator.comparingLong(Transaction::getTimestamp)
                .thenComparing(Comparator.comparingLong(Transaction::getAmount).reversed()));

        for(Transaction tx : txns){
            System.out.println(tx);
        }

        // null handling
        txns.add(null);
        txns.sort(Comparator.nullsFirst(Comparator.comparingLong(Transaction::getTimestamp)
                .thenComparing(Comparator.comparingLong(Transaction::getAmount).reversed())));

        for(Transaction tx : txns){
            System.out.println(tx);
        }
    }
}

class Transaction{
    String accountId;
    Long timestamp;
    Long amount;

    @Override
    public String toString() {
        return "Transaction{" +
                "accountId='" + accountId + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }

    public Transaction(String accountId, Long timestamp, Long amount) {
        this.accountId = accountId;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
