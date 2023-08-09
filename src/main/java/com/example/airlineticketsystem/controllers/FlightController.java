package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.responses.FlightGetDto;
import com.example.airlineticketsystem.services.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("searchByCode")
    public List<FlightGetDto> searchByCode(@RequestParam String code) {
        return flightService.searchByCode(code);
    }
}
