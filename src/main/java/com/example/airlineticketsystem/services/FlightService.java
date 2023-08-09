package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.dtos.responses.FlightGetDto;
import com.example.airlineticketsystem.entities.Flight;

import java.util.List;

public interface FlightService {
    Flight add(Flight flight);

    List<FlightGetDto> searchByCode(String code);

    Flight findByCode(String code);
}
