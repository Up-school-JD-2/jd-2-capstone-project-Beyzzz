package com.example.airlineticketsystem.dtos.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class RouteGetDto {
    private Long id;
    private List<AirportRouteGetDto> airportRouteGetDtoList;
}
