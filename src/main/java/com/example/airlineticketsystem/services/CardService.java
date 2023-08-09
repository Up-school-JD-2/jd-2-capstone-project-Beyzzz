package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.entities.Card;

import java.util.Optional;

public interface CardService {

    Card add(Card card);

    Optional<Card> findById(Long id);

    Card findByHolderAndNumber(String holder, String number);

}
