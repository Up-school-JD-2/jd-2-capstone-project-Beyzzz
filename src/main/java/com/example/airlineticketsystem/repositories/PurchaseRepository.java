package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByTicket_IdAndCard_Id(Long ticketId, Long cardId);
}
