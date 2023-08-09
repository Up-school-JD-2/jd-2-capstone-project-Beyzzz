package com.example.airlineticketsystem.customExceptions;

public class TicketNoLongerAvailableException extends ApplicationException {

    public TicketNoLongerAvailableException(String message) {
        super(message);
    }

    public TicketNoLongerAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
