package com.example.airlineticketsystem.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class AirportRouteGetDto {
    private Integer turn;
    private String airportName;
}
