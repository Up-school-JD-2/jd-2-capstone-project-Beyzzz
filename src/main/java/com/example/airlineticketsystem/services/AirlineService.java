package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.dtos.requests.AirlineAddDto;
import com.example.airlineticketsystem.dtos.requests.FlightAddDto;
import com.example.airlineticketsystem.dtos.responses.AirlineGetDto;

import java.util.List;

public interface AirlineService {
    AirlineGetDto add(AirlineAddDto airlineAddDto);

    List<AirlineGetDto> searchByName(String name);

    AirlineGetDto addFlightToAirline(Long id, FlightAddDto flightAddDto);

}
