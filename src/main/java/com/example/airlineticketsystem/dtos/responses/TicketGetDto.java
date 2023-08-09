package com.example.airlineticketsystem.dtos.responses;

import com.example.airlineticketsystem.entities.TicketStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class TicketGetDto {

    private Long id;
    private BigDecimal price;
    private String number;
    private TicketStatus status;
    private FlightGetDto flightGetDto;

}
