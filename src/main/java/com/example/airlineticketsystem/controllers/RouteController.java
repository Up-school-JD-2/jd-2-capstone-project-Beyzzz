package com.example.airlineticketsystem.controllers;

import com.example.airlineticketsystem.dtos.requests.RouteAddDto;
import com.example.airlineticketsystem.dtos.responses.RouteGetDto;
import com.example.airlineticketsystem.services.RouteService;
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
    public RouteGetDto add(@RequestBody @Validated RouteAddDto routeAddDto) {
        return routeService.add(routeAddDto);
    }

    @GetMapping("searchById/{id}")
    public RouteGetDto searchById(@PathVariable Long id) {
        return routeService.searchById(id);
    }

}
