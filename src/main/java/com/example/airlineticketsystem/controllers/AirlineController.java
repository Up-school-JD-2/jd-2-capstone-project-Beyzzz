package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.AirlineAddDto;
import com.example.airlineticketsystem.dtos.requests.FlightAddDto;
import com.example.airlineticketsystem.dtos.responses.AirlineGetDto;
import com.example.airlineticketsystem.services.AirlineService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping("add")
    public AirlineGetDto add(@RequestBody @Validated AirlineAddDto airlineAddDto) {
        return airlineService.add(airlineAddDto);
    }

    @GetMapping("searchByName")
    public List<AirlineGetDto> searchByName(@RequestParam String name) {
        return airlineService.searchByName(name);
    }


    @PostMapping("addFlight")
    public AirlineGetDto addFlightToAirline(@RequestParam Long airlineId, @RequestBody @Validated FlightAddDto flightAddDto) {
        return airlineService.addFlightToAirline(airlineId, flightAddDto);
    }


}
