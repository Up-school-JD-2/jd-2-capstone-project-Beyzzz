package com.example.airlineticketsystem.customExceptions;

public class InvalidCardException extends ApplicationException {
    public InvalidCardException(String message) {
        super(message);
    }

    public InvalidCardException(String message, Throwable cause) {
        super(message, cause);
    }
}
