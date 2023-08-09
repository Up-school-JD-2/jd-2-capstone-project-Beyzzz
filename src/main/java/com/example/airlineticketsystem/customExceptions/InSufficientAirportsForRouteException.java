package com.example.airlineticketsystem.customExceptions;

public class InSufficientAirportsForRouteException extends ApplicationException {

    public InSufficientAirportsForRouteException(String message) {
        super(message);
    }

    public InSufficientAirportsForRouteException(String message, Throwable cause) {
        super(message, cause);
    }
}
