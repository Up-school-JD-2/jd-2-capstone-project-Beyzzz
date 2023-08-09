package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.dtos.requests.AirportAddDto;
import com.example.airlineticketsystem.dtos.responses.AirportGetDto;
import com.example.airlineticketsystem.entities.Airport;

import java.util.List;

public interface AirportService {
    AirportGetDto add(AirportAddDto airportAddDto);

    List<AirportGetDto> searchByName(String name);

    Airport findByCode(String code);


}
