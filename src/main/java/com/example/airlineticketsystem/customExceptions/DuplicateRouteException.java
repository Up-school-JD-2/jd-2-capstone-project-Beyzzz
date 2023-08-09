package com.example.airlineticketsystem.customExceptions;

public class DuplicateRouteException extends ApplicationException {
    public DuplicateRouteException(String message) {
        super(message);
    }

    public DuplicateRouteException(String message, Throwable cause) {
        super(message, cause);
    }
}
