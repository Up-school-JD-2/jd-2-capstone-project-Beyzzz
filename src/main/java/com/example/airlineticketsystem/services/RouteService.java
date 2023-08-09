package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.dtos.requests.RouteAddDto;
import com.example.airlineticketsystem.dtos.responses.RouteGetDto;
import com.example.airlineticketsystem.entities.Route;

public interface RouteService {
    RouteGetDto add(RouteAddDto route);

    RouteGetDto searchById(Long id);

    Route findById(Long id);
}
