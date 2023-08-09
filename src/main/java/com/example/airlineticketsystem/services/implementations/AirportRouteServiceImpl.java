package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.entities.AirportRoute;
import com.example.airlineticketsystem.repositories.AirportRouteRepository;
import com.example.airlineticketsystem.services.AirportRouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportRouteServiceImpl implements AirportRouteService {
    private final AirportRouteRepository airportRouteRepository;

    public AirportRouteServiceImpl(AirportRouteRepository airportRouteRepository) {
        this.airportRouteRepository = airportRouteRepository;
    }

    @Override
    public AirportRoute save(AirportRoute airportRoute) {
        return airportRouteRepository.save(airportRoute);
    }

    @Override
    public List<AirportRoute> findAllByRouteId(Long routeId) {
        return airportRouteRepository.findAllByRoute_Id(routeId);
    }

    @Override
    public AirportRoute findById(Long id) {
        return airportRouteRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("AirportRoute could not be found!"));
    }
}
