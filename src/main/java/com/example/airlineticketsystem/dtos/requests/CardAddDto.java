package com.example.airlineticketsystem.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class CardAddDto {

    @NotNull
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvv;

    @NotNull
    private LocalDate deadline;

    @NotBlank
    private String holder;

    @NotNull
    private BigDecimal balance;

}
