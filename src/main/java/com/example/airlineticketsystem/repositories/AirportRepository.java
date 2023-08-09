package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, String> {
    @Query(nativeQuery = true, value = "select * from airports where name like %:name%")
    List<Airport> findByName(@Param("name") String name);

}
