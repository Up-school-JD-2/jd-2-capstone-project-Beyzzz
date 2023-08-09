package com.example.airlineticketsystem.customExceptions;

public class InSufficientCardBalanceException extends ApplicationException {

    public InSufficientCardBalanceException(String message) {
        super(message);
    }

    public InSufficientCardBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
