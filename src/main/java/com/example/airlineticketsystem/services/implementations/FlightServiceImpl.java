package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.dtos.responses.FlightGetDto;
import com.example.airlineticketsystem.entities.Flight;
import com.example.airlineticketsystem.repositories.FlightRepository;
import com.example.airlineticketsystem.services.FlightService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Flight add(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<FlightGetDto> searchByCode(String code) {
        return flightRepository.findByCode(code).stream()
                .map(flight -> modelMapper.map(flight, FlightGetDto.class))
                .toList();
    }

    @Override
    public Flight findByCode(String code) {
        return flightRepository.findById(code).orElseThrow(() -> new EntityNotFoundException("Flight could not be found!"));
    }


}
