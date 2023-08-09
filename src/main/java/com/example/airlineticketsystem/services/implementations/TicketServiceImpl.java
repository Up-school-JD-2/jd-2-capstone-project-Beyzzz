package com.example.airlineticketsystem.services.implementations;

import com.example.airlineticketsystem.customExceptions.InSufficientCardBalanceException;
import com.example.airlineticketsystem.customExceptions.InvalidCardException;
import com.example.airlineticketsystem.customExceptions.TicketAlreadySoldException;
import com.example.airlineticketsystem.customExceptions.TicketNoLongerAvailableException;
import com.example.airlineticketsystem.dtos.requests.CardAddDto;
import com.example.airlineticketsystem.dtos.requests.TicketAddDto;
import com.example.airlineticketsystem.dtos.responses.FlightGetDto;
import com.example.airlineticketsystem.dtos.responses.PurchaseGetDto;
import com.example.airlineticketsystem.dtos.responses.TicketGetDto;
import com.example.airlineticketsystem.entities.*;
import com.example.airlineticketsystem.repositories.TicketRepository;
import com.example.airlineticketsystem.services.CardService;
import com.example.airlineticketsystem.services.FlightService;
import com.example.airlineticketsystem.services.PurchaseService;
import com.example.airlineticketsystem.services.TicketService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final FlightService flightService;
    private final CardService cardService;
    private final PurchaseService purchaseService;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, FlightService flightService, CardService cardService, PurchaseService purchaseService) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.flightService = flightService;
        this.cardService = cardService;
        this.purchaseService = purchaseService;
    }

    @Override
    @Transactional
    public PurchaseGetDto purchaseTicket(Long ticketId, CardAddDto cardAddDto) {

        Card card = cardService.findById(cardAddDto.getId()).orElse(validateCardNumberConvertToCard(cardAddDto));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket could not be found!"));

        BigDecimal currentTicketPrice = ticket.getPrice();

        if (LocalDate.now().isAfter(ticket.getFlight().getDate())) {
            throw new TicketNoLongerAvailableException("The flight took place, so that " +
                    "this ticket unavailable for purchase or cancellation!");
        }

        if (LocalDate.now().isEqual(ticket.getFlight().getDate()) &&
                LocalTime.now().isAfter(ticket.getFlight().getDepartureTime())) {
            throw new TicketNoLongerAvailableException("The flight took place, so that " +
                    "this ticket unavailable for purchase or cancellation!");
        }

        if (ticket.getStatus().equals(TicketStatus.SOLD)) {
            throw new TicketAlreadySoldException("This ticket is already sold!");
        }

        if (card.getDeadline().isBefore(LocalDate.now())) {
            throw new InvalidCardException("The card deadline is past!");
        }

        if (card.getBalance().compareTo(ticket.getPrice()) < 0) {
            throw new InSufficientCardBalanceException("The card balance is insufficient for the ticket price!");
        }

        BigDecimal remainingBalance = card.getBalance().subtract(currentTicketPrice);
        card.setBalance(remainingBalance);
        cardService.add(card);

        ticket.setStatus(TicketStatus.SOLD);

        Purchase purchase = new Purchase();
        purchase.setTicket(ticket);
        purchase.setCard(card);
        purchase.setDatetime(LocalDateTime.now());
        purchase.setPrice(currentTicketPrice);
        purchaseService.add(purchase);

        PurchaseGetDto purchaseGetDto = modelMapper.map(purchase, PurchaseGetDto.class);

        FlightGetDto flightGetDto = modelMapper.map(ticket.getFlight(), FlightGetDto.class);

        purchaseGetDto.setFlightGetDto(flightGetDto);

        return purchaseGetDto;
    }

    private Card validateCardNumberConvertToCard(CardAddDto cardAddDto) {
        String maskedCardNumber = validateAndMaskCardNumber(cardAddDto.getNumber());
        cardAddDto.setNumber(maskedCardNumber);
        return modelMapper.map(cardAddDto, Card.class);
    }

    private String validateAndMaskCardNumber(String cardNumber) {
        // cardNumber.replaceAll("[\\s\\-,]", ""); //boşlukları, tireleri ve virgüller kart numarasından siler
        String purifiedCardNumber = cardNumber.replaceAll("\\D", ""); //rakam haricindeki tüm karakterleri kart numarasından siler
        if (purifiedCardNumber.length() != 16) {
            throw new InvalidCardException("The card number is invalid!");
        }
        return purifiedCardNumber.substring(0, 6) + "******" + purifiedCardNumber.substring(12, 16);
    }


    @Override
    public TicketGetDto add(TicketAddDto ticketAddDto) {

        Ticket ticket = modelMapper.map(ticketAddDto, Ticket.class);
        Flight flight = flightService.findByCode(ticketAddDto.getFlightCode());
        ticket.setFlight(flight);

        TicketGetDto ticketGetDto = modelMapper.map(ticketRepository.save(ticket), TicketGetDto.class);
        FlightGetDto flightGetDto = modelMapper.map(flight, FlightGetDto.class);

        ticketGetDto.setFlightGetDto(flightGetDto);
        return ticketGetDto;

    }

    @Override
    public TicketGetDto searchByTicketNumber(String ticketNumber) {

        Ticket ticket = ticketRepository.findByNumber(ticketNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cloud not find the ticket with the given ticket number"));

        TicketGetDto ticketGetDto = modelMapper.map(ticket, TicketGetDto.class);
        FlightGetDto flightGetDto = modelMapper.map(ticket.getFlight(), FlightGetDto.class);
        ticketGetDto.setFlightGetDto(flightGetDto);

        return ticketGetDto;
    }

    @Override
    @Transactional
    public TicketGetDto cancelTicket(String ticketNumber, String cardHolder, String cardNumber) {

        String validatedAndMaskedCardNumber = validateAndMaskCardNumber(cardNumber);

        Ticket ticket = ticketRepository.findByNumber(ticketNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cloud not find the ticket with the given ticket number"));

        if (LocalDate.now().isAfter(ticket.getFlight().getDate())) {
            throw new TicketNoLongerAvailableException("The flight took place, so that " +
                    "this ticket unavailable for purchase or cancellation!");
        }

        if (LocalDate.now().isEqual(ticket.getFlight().getDate()) &&
                LocalTime.now().isAfter(ticket.getFlight().getDepartureTime())) {
            throw new TicketNoLongerAvailableException("The flight took place, so that " +
                    "this ticket unavailable for purchase or cancellation!");
        }

        ticket.setStatus(TicketStatus.ON_SALE);
        Card card = cardService.findByHolderAndNumber(cardHolder, validatedAndMaskedCardNumber);
        Purchase purchase = purchaseService.findByTicketIdAndCardId(ticket.getId(), card.getId());

        BigDecimal purchasedPriceOfTicket = purchase.getPrice();

        card.setBalance(card.getBalance().add(purchasedPriceOfTicket.multiply(BigDecimal.valueOf(0.6))));

        TicketGetDto ticketGetDto = modelMapper.map(ticket, TicketGetDto.class);
        FlightGetDto flightGetDto = modelMapper.map(ticket.getFlight(), FlightGetDto.class);
        ticketGetDto.setFlightGetDto(flightGetDto);

        return ticketGetDto;
    }

}
