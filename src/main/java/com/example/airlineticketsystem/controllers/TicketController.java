package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.CardAddDto;
import com.example.airlineticketsystem.dtos.requests.TicketAddDto;
import com.example.airlineticketsystem.dtos.responses.PurchaseGetDto;
import com.example.airlineticketsystem.dtos.responses.TicketGetDto;
import com.example.airlineticketsystem.services.TicketService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("add")
    public TicketGetDto add(@RequestBody @Validated TicketAddDto ticketAddDto) {
        return ticketService.add(ticketAddDto);
    }

    @PostMapping("purchase/{ticketId}")
    public PurchaseGetDto purchaseTicket(@PathVariable Long ticketId, @RequestBody @Validated CardAddDto cardAddDto) {
        return ticketService.purchaseTicket(ticketId, cardAddDto);
    }

    @PostMapping("cancel")
    public TicketGetDto cancelTicket(@RequestParam String ticketNumber, @RequestParam String cardHolder, @RequestParam String cardNumber) {
        return ticketService.cancelTicket(ticketNumber, cardHolder, cardNumber);
    }

    @GetMapping("findByTicketNumber")
    public TicketGetDto searchByTicketNumber(@RequestParam String ticketNumber) {
        return ticketService.searchByTicketNumber(ticketNumber);
    }

}
