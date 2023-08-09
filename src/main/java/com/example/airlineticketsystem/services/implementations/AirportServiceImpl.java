package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.dtos.requests.AirportAddDto;
import com.example.airlineticketsystem.dtos.responses.AirportGetDto;
import com.example.airlineticketsystem.entities.Airport;
import com.example.airlineticketsystem.repositories.AirportRepository;
import com.example.airlineticketsystem.services.AirportService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public AirportServiceImpl(AirportRepository airportRepository, ModelMapper modelMapper) {
        this.airportRepository = airportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AirportGetDto add(AirportAddDto airportAddDto) {
        Airport airport = modelMapper.map(airportAddDto, Airport.class);
        return modelMapper.map(airportRepository.save(airport), AirportGetDto.class);
    }

    @Override
    public List<AirportGetDto> searchByName(String name) {
        List<Airport> airports = airportRepository.findByName(name);
        return airports.stream().map(airport -> modelMapper.map(airport, AirportGetDto.class)).toList();
    }

    @Override
    public Airport findByCode(String code) {
        return airportRepository.findById(code).
                orElseThrow(() -> new EntityNotFoundException("Airport could not be found!"));
    }


}
