package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.AirportRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRouteRepository extends JpaRepository<AirportRoute, Long> {

    List<AirportRoute> findAllByRoute_Id(Long routeId);

}
