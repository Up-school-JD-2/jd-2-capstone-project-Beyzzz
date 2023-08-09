package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByNumber(String name);
}
