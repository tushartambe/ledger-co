package org.ledgerco.Exception;

public class LoanDetailsNotFoundException extends RuntimeException {
    public LoanDetailsNotFoundException(String message) {
        super(message);
    }
}
