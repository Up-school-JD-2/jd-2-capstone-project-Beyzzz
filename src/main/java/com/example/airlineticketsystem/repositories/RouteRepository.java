package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
