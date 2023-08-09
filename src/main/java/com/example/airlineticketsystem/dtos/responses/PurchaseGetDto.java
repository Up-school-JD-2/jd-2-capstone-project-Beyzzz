package com.example.airlineticketsystem.dtos.responses;

import com.example.airlineticketsystem.entities.TicketStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class PurchaseGetDto {
    private Long purchaseId;
    private Long ticketId;
    private String purchaser;
    private String cardNumber;
    private BigDecimal purchasedPrice;
    private BigDecimal cardRemainingBalance;
    private LocalDateTime purchaseLocalDateTime;
    private String ticketNumber;
    private TicketStatus ticketStatus;
    private FlightGetDto flightGetDto;

}
