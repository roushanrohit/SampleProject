package org.oops.basics.callback;

import java.util.List;

public class TransactionService {

    public void fetchTransactions(TransactionCallback callback) {

        // Simulate fetching data from a backend API

        // Simulate API failure
        boolean apiCallSuccessful = false;

        if (apiCallSuccessful) {
            callback.onSuccess(List.of("Salary: +₹80,000", "Rent: -₹20,000"));
        } else {
            callback.onError("Unable to fetch transactions");
        }
    }
}
