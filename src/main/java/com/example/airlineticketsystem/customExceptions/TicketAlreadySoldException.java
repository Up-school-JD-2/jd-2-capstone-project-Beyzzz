package com.example.airlineticketsystem.customExceptions;

public class TicketAlreadySoldException extends ApplicationException {
    public TicketAlreadySoldException(String message) {
        super(message);
    }

    public TicketAlreadySoldException(String message, Throwable cause) {
        super(message, cause);
    }
}
