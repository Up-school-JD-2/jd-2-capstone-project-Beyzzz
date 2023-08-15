package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.BaseResponse;
import com.example.airlineticketsystem.dtos.requests.RouteAddDto;
import com.example.airlineticketsystem.dtos.responses.RouteGetDto;
import com.example.airlineticketsystem.services.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Validated RouteAddDto routeAddDto) {
        var routeGetDto = routeService.add(routeAddDto);
        var response = BaseResponse.<RouteGetDto>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("searchById/{id}")
    public ResponseEntity<?> searchById(@PathVariable Long id) {
        var routeGetDto = routeService.searchById(id);
        var response = BaseResponse.<RouteGetDto>builder()
                .isSuccess(true)
                .data(routeGetDto)
                .build();
        return ResponseEntity.ok(response);
    }

}
