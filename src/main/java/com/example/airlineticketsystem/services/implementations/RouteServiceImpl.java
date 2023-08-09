package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.customExceptions.DuplicateAirportInRoute;
import com.example.airlineticketsystem.customExceptions.DuplicateRouteException;
import com.example.airlineticketsystem.customExceptions.InSufficientAirportsForRouteException;
import com.example.airlineticketsystem.dtos.requests.AirportRouteAddDto;
import com.example.airlineticketsystem.dtos.requests.RouteAddDto;
import com.example.airlineticketsystem.dtos.responses.AirportRouteGetDto;
import com.example.airlineticketsystem.dtos.responses.RouteGetDto;
import com.example.airlineticketsystem.entities.Airport;
import com.example.airlineticketsystem.entities.AirportRoute;
import com.example.airlineticketsystem.entities.Route;
import com.example.airlineticketsystem.repositories.RouteRepository;
import com.example.airlineticketsystem.services.AirportRouteService;
import com.example.airlineticketsystem.services.AirportService;
import com.example.airlineticketsystem.services.RouteService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final AirportRouteService airportRouteService;
    private final AirportService airportService;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, AirportRouteService airportRouteService, AirportService airportService, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.airportRouteService = airportRouteService;
        this.airportService = airportService;
        this.modelMapper = modelMapper;
    }

    @Override
    public RouteGetDto add(RouteAddDto routeAddDto) {


        //1. kontrol: bir havaalanı birden fazla kez bir rotada girilemez
        boolean noDuplicates = routeAddDto.getAirportRouteAddDtoList()
                .stream().allMatch(new HashSet<>()::add);

        if (!noDuplicates) {
            throw new DuplicateAirportInRoute("There are duplicate airports in the route!");
        }

        //2. kontrol
        if (routeAddDto.getAirportRouteAddDtoList().size() < 2) {
            throw new InSufficientAirportsForRouteException("The number of airports is insufficient for a route!");
        }

        //3. kontrol veri tabanındaki  rota kodlarını joinledim
        List<Route> routes = routeRepository.findAll();
        List<String> routeCombinedCodes = new ArrayList<>();
        for (Route route : routes) {
            List<AirportRoute> airportRoutes = airportRouteService.findAllByRouteId(route.getId());
            String routeCombinedCode = airportRoutes.stream()
                    .map(airportRoute -> airportRoute.getAirport().getCode())
                    .collect(Collectors.joining());
            routeCombinedCodes.add(routeCombinedCode);
        }

        // Bize verilen rota kodunu joinledim
        List<AirportRouteAddDto> airportRouteAddDtoList = routeAddDto.getAirportRouteAddDtoList();

        String routeCombinedCodeToCheck = airportRouteAddDtoList.stream()
                .map(AirportRouteAddDto::getAirportCode)
                .collect(Collectors.joining());


        // Bu string elimdeki listede var mı kontrolü yaptım
        if (routeCombinedCodes.contains(routeCombinedCodeToCheck)) {
            throw new DuplicateRouteException("Could not add duplicate route!");
        }

        //kontrollerin sonu

        Route route = routeRepository.save(new Route());

        // Rotanın kendisinde havaalanı yok o yuzden airportRoute dan list oluşturdum.
        List<AirportRoute> airportRouteList = new ArrayList<>();

        int turn = 1;

        for (AirportRouteAddDto airportRouteAddDto : airportRouteAddDtoList) {
            Airport airport = airportService.findByCode(airportRouteAddDto.getAirportCode());
            AirportRoute airportRoute = new AirportRoute();
            airportRoute.setTurn(turn);
            airportRoute.setRoute(route);
            airportRoute.setAirport(airport);
            airportRoute = airportRouteService.save(airportRoute);
            airportRouteList.add(airportRoute);
            turn++;
        }

        RouteGetDto routeGetDto = new RouteGetDto();
        routeGetDto.setId(route.getId());
        List<AirportRouteGetDto> airportRouteGetDtoList = airportRouteList.stream().map(airportRoute ->
                modelMapper.map(airportRoute, AirportRouteGetDto.class)).toList();

        routeGetDto.setAirportRouteGetDtoList(airportRouteGetDtoList);

        return routeGetDto;
    }

    @Override
    public RouteGetDto searchById(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route could not be found!"));
        RouteGetDto routeGetDto = new RouteGetDto();
        routeGetDto.setId(route.getId());
        List<AirportRouteGetDto> airportRouteGetDtoList = airportRouteService.findAllByRouteId(id)
                .stream()
                .map(airportRoute -> modelMapper.map(airportRoute, AirportRouteGetDto.class))
                .toList();

        routeGetDto.setAirportRouteGetDtoList(airportRouteGetDtoList);
        return routeGetDto;
    }

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route could not be found!"));
    }


}
