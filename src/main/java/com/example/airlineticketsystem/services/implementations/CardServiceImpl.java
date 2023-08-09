package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.entities.Card;
import com.example.airlineticketsystem.repositories.CardRepository;
import com.example.airlineticketsystem.services.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card add(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card findByHolderAndNumber(String holder, String number) {
        return cardRepository.findByHolderAndNumber(holder, number)
                .orElseThrow(() -> new EntityNotFoundException("The card could not be found!"));
    }


}
