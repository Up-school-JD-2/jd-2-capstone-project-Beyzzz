package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.dtos.requests.AirlineAddDto;
import com.example.airlineticketsystem.dtos.requests.FlightAddDto;
import com.example.airlineticketsystem.dtos.responses.AirlineGetDto;
import com.example.airlineticketsystem.dtos.responses.FlightGetDto;
import com.example.airlineticketsystem.entities.Airline;
import com.example.airlineticketsystem.entities.Flight;
import com.example.airlineticketsystem.entities.Route;
import com.example.airlineticketsystem.repositories.AirlineRepository;
import com.example.airlineticketsystem.services.AirlineService;
import com.example.airlineticketsystem.services.FlightService;
import com.example.airlineticketsystem.services.RouteService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final RouteService routeService;
    private final FlightService flightService;
    private final ModelMapper modelMapper;

    public AirlineServiceImpl(AirlineRepository airlineRepository, RouteService routeService, FlightService flightService, ModelMapper modelMapper) {
        this.airlineRepository = airlineRepository;
        this.routeService = routeService;
        this.flightService = flightService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AirlineGetDto add(AirlineAddDto airlineAddDto) {
        Airline airline = modelMapper.map(airlineAddDto, Airline.class);
        return modelMapper.map(airlineRepository.save(airline), AirlineGetDto.class);
    }

    @Override
    public List<AirlineGetDto> searchByName(String name) {
        List<Airline> airlines = airlineRepository.findByName(name);

        List<AirlineGetDto> airlineGetDtoList = new ArrayList<>();

        for (Airline airline : airlines) {
            AirlineGetDto airlineGetDto = modelMapper.map(airline, AirlineGetDto.class);

            List<Flight> flightList = airline.getFlightList();
            List<FlightGetDto> flightGetDtoList = flightList.stream().map(f -> modelMapper.map(f, FlightGetDto.class)).toList();

            airlineGetDto.setFlightGetDtoList(flightGetDtoList);
            airlineGetDtoList.add(airlineGetDto);
        }
        return airlineGetDtoList;
    }


    @Override
    public AirlineGetDto addFlightToAirline(Long airlineId, FlightAddDto flightAddDto) {

        Flight flight = modelMapper.map(flightAddDto, Flight.class);

        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find airline with the given id!"));
        Route route = routeService.findById(flightAddDto.getRouteId());
        flight.setRoute(route);
        flight.setAirline(airline);
        flightService.add(flight);

        AirlineGetDto airlineGetDto = modelMapper.map(airline, AirlineGetDto.class);

        List<Flight> flightList = airline.getFlightList();
        List<FlightGetDto> flightGetDtoList = flightList.stream().map(f -> modelMapper.map(f, FlightGetDto.class)).toList();

        airlineGetDto.setFlightGetDtoList(flightGetDtoList);
        return airlineGetDto;
    }

}




