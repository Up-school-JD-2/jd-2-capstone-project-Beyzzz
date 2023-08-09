package com.example.airlineticketsystem.dtos.requests;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class RouteAddDto {

    @Valid
    private List<AirportRouteAddDto> airportRouteAddDtoList;

}
