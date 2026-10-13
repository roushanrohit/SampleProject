package org.oops.basics.callback;

import java.util.List;

public interface TransactionCallback {

    void onSuccess(List<String> transactions);

    void onError(String error);
}
