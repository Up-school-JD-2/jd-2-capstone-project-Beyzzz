package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.AirlineAddDto;
import com.example.airlineticketsystem.dtos.requests.BaseResponse;
import com.example.airlineticketsystem.dtos.requests.FlightAddDto;
import com.example.airlineticketsystem.dtos.responses.AirlineGetDto;
import com.example.airlineticketsystem.services.AirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> add(@RequestBody @Validated AirlineAddDto airlineAddDto) {
        var airlineGetDto = airlineService.add(airlineAddDto);
        var response = BaseResponse.<AirlineGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airlineGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("searchByName")
    public ResponseEntity<List<AirlineGetDto>> searchByName(@RequestParam String name) {
        var airlineGetDtoList = airlineService.searchByName(name);
        return ResponseEntity.ok(airlineGetDtoList);
    }


    @PostMapping("addFlight")
    public ResponseEntity<?> addFlightToAirline(@RequestParam Long airlineId, @RequestBody @Validated FlightAddDto flightAddDto) {
        var airlineGetDto = airlineService.addFlightToAirline(airlineId, flightAddDto);
        var response = BaseResponse.<AirlineGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airlineGetDto)
                .build();
        return ResponseEntity.ok(response);
    }


}
