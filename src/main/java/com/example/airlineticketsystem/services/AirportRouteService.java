package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.entities.AirportRoute;

import java.util.List;

public interface AirportRouteService {

    AirportRoute findById(Long id);

    AirportRoute save(AirportRoute airportRoute);

    List<AirportRoute> findAllByRouteId(Long routeId);

}
