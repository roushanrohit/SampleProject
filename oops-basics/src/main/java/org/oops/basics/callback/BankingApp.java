package org.oops.basics.callback;

import java.util.List;

public class BankingApp {

    public static void main(String[] args) {

        TransactionService service = new TransactionService();

        service.fetchTransactions(new TransactionCallback() {

            @Override
            public void onSuccess(List<String> transactions) {
                System.out.println("Displaying transactions:");

                for (String transaction : transactions) {
                    System.out.println(transaction);
                }
            }

            @Override
            public void onError(String error) {
                System.out.println("Failed: " + error);
            }
        });
    }
}
