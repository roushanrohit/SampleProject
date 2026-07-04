package org.gitvcs.exception;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException(String userId) {
        super("User not registered: " + userId);
    }
}
