package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.AirportAddDto;
import com.example.airlineticketsystem.dtos.responses.AirportGetDto;
import com.example.airlineticketsystem.services.AirportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("add")
    public AirportGetDto add(@RequestBody @Validated AirportAddDto airportAddDto) {
        return airportService.add(airportAddDto);
    }

    @GetMapping("searchByName")
    public List<AirportGetDto> searchByName(@RequestParam String name) {
        return airportService.searchByName(name);
    }
}
