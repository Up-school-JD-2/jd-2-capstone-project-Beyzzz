package com.example.airlineticketsystem.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class FlightGetDto {

    private String code;
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;
    private Long routeId;
    private String airlineName;


}
