package com.example.airlineticketsystem.services;

import com.example.airlineticketsystem.entities.Purchase;

public interface PurchaseService {
    Purchase add(Purchase purchase);

    Purchase findById(Long id);

    Purchase findByTicketIdAndCardId(Long ticketId, Long cardId);

}
