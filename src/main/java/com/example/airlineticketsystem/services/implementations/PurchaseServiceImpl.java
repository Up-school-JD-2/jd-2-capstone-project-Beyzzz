package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.entities.Purchase;
import com.example.airlineticketsystem.repositories.PurchaseRepository;
import com.example.airlineticketsystem.services.PurchaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public Purchase add(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The purchase could not be found!"));
    }

    @Override
    public Purchase findByTicketIdAndCardId(Long ticketId, Long cardId) {
        return purchaseRepository.findByTicket_IdAndCard_Id(ticketId, cardId)
                .orElseThrow(() -> new EntityNotFoundException("The purchase could not be found!"));
    }
}
