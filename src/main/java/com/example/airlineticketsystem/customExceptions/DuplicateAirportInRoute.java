package com.example.airlineticketsystem.customExceptions;

public class DuplicateAirportInRoute extends ApplicationException {

    public DuplicateAirportInRoute(String message) {
        super(message);
    }

    public DuplicateAirportInRoute(String message, Throwable cause) {
        super(message, cause);
    }
}
