package com.example.airlineticketsystem.dtos.requests;

import com.example.airlineticketsystem.entities.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class TicketAddDto {
    @NotNull
    private BigDecimal price;

    @NotBlank
    @Size(min = 13, max = 13)
    private String number;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @NotBlank
    private String flightCode;
}
