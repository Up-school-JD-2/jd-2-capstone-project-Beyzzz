package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query(nativeQuery = true, value = "select * from flights where code like %:code%")
    List<Flight> findByCode(@Param("code") String code);

}
