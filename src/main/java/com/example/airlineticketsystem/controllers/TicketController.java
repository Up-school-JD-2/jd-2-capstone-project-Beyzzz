package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.BaseResponse;
import com.example.airlineticketsystem.dtos.requests.CardAddDto;
import com.example.airlineticketsystem.dtos.requests.TicketAddDto;
import com.example.airlineticketsystem.dtos.responses.PurchaseGetDto;
import com.example.airlineticketsystem.dtos.responses.TicketGetDto;
import com.example.airlineticketsystem.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> add(@RequestBody @Validated TicketAddDto ticketAddDto) {
        var ticketGetDto = ticketService.add(ticketAddDto);
        var response = BaseResponse.<TicketGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("purchase/{ticketId}")
    public ResponseEntity<?> purchaseTicket(@PathVariable Long ticketId, @RequestBody @Validated CardAddDto cardAddDto) {
        var purchaseGetDto = ticketService.purchaseTicket(ticketId, cardAddDto);
        var response = BaseResponse.<PurchaseGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(purchaseGetDto)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping("cancel")
    public ResponseEntity<?> cancelTicket(@RequestParam String ticketNumber, @RequestParam String cardHolder, @RequestParam String cardNumber) {
        var ticketGetDto = ticketService.cancelTicket(ticketNumber, cardHolder, cardNumber);
        var response = BaseResponse.<TicketGetDto>builder()
                .isSuccess(true)
                .data(ticketGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findByTicketNumber")
    public ResponseEntity<?> searchByTicketNumber(@RequestParam String ticketNumber) {
        var routeGetDto = ticketService.searchByTicketNumber(ticketNumber);
        var response = BaseResponse.<TicketGetDto>builder()
                .isSuccess(true)
                .data(routeGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

}
