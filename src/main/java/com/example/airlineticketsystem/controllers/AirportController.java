package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.AirportAddDto;
import com.example.airlineticketsystem.dtos.requests.BaseResponse;
import com.example.airlineticketsystem.dtos.responses.AirlineGetDto;
import com.example.airlineticketsystem.dtos.responses.AirportGetDto;
import com.example.airlineticketsystem.services.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> add(@RequestBody @Validated AirportAddDto airportAddDto) {
        var airportGetDto = airportService.add(airportAddDto);
        var response = BaseResponse.<AirportGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airportGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("searchByName")
    public ResponseEntity<List<AirportGetDto>> searchByName(@RequestParam String name) {
        var airportGetDtoList = airportService.searchByName(name);
        return ResponseEntity.ok(airportGetDtoList);
    }
}
