package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.dtos.requests.CardAddDto;
import com.example.airlineticketsystem.dtos.requests.TicketAddDto;
import com.example.airlineticketsystem.dtos.responses.PurchaseGetDto;
import com.example.airlineticketsystem.dtos.responses.TicketGetDto;

public interface TicketService {

    PurchaseGetDto purchaseTicket(Long ticketId, CardAddDto card);

    TicketGetDto add(TicketAddDto ticketAddDto);

    TicketGetDto searchByTicketNumber(String ticketNumber);

    TicketGetDto cancelTicket(String ticketNumber, String cardHolder, String cardNumber);

}
