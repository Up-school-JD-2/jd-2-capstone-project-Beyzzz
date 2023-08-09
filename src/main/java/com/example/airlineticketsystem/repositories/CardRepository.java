package com.example.airlineticketsystem.repositories;

import com.example.airlineticketsystem.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByHolderAndNumber(String holder, String number);

}
