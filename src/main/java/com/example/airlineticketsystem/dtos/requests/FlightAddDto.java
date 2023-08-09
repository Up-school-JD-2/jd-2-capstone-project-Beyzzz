package com.example.airlineticketsystem.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class FlightAddDto {

    @NotBlank
    private String code;

    @NotNull
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    @NotNull
    private Long routeId;

}
