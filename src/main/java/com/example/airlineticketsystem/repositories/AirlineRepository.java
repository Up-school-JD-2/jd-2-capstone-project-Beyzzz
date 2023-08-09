package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Query(nativeQuery = true, value = "select * from airlines where name like %:name%")
    List<Airline> findByName(@Param("name") String name);

}
