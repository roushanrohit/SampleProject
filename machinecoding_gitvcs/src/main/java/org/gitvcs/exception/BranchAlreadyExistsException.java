package org.gitvcs.exception;

public class BranchAlreadyExistsException extends RuntimeException {
    public BranchAlreadyExistsException(String branchName) {
        super("Branch already exists: " + branchName);
    }
}
