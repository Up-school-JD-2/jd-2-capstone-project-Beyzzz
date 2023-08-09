package com.example.airlineticketsystem.configurations;

import com.example.airlineticketsystem.dtos.requests.*;
import com.example.airlineticketsystem.dtos.responses.*;
import com.example.airlineticketsystem.entities.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final Converter<AirlineAddDto, Airline>

            AIRLINE_ADD_DTO_TO_AIRLINE_CONVERTER =
            (context) -> {

                AirlineAddDto airlineAddDto = context.getSource();
                Airline airline = Airline.builder()
                        .name(airlineAddDto.getName())
                        .build();

                System.out.println("AIRLINE_ADD_DTO_TO_AIRLINE_CONVERTER: " + airline);

                return airline;
            };

    private static final Converter<Airline, AirlineGetDto>

            AIRLINE_TO_AIRLINE_GET_DTO_CONVERTER =
            (context) -> {

                Airline airline = context.getSource();
                AirlineGetDto airlineGetDto = AirlineGetDto.builder()
                        .id(airline.getId())
                        .name(airline.getName())
                        .build();

                System.out.println("AIRLINE_TO_AIRLINE_GET_DTO_CONVERTER: " + airlineGetDto);

                return airlineGetDto;
            };


    private static final Converter<AirportAddDto, Airport>

            AIRPORT_ADD_DTO_TO_AIRPORT_CONVERTER =
            (context) -> {

                AirportAddDto airportAddDto = context.getSource();
                Airport airport = Airport.builder()
                        .code(airportAddDto.getCode())
                        .name(airportAddDto.getName())
                        .build();

                System.out.println("AIRPORT_ADD_DTO_TO_AIRPORT_CONVERTER: " + airport);

                return airport;
            };

    private static final Converter<Airport, AirportGetDto>

            AIRPORT_TO_AIRPORT_GET_DTO_CONVERTER =
            (context) -> {

                Airport airport = context.getSource();
                AirportGetDto airportGetDto = AirportGetDto.builder()
                        .code(airport.getCode())
                        .name(airport.getName())
                        .build();

                System.out.println("AIRPORT_TO_AIRPORT_GET_DTO_CONVERTER: " + airportGetDto);

                return airportGetDto;
            };
    private static final Converter<AirportRoute, AirportRouteGetDto>

            AIRPORT_ROUTE_TO_AIRPORT_ROUTE_GET_DTO_CONVERTER =
            (context) -> {

                AirportRoute airportRoute = context.getSource();
                AirportRouteGetDto airportRouteGetDto = AirportRouteGetDto.builder()
                        .turn(airportRoute.getTurn())
                        .airportName(airportRoute.getAirport().getName())
                        .build();

                System.out.println("AIRPORT_ROUTE_TO_AIRPORT_ROUTE_GET_DTO_CONVERTER: " + airportRouteGetDto);

                return airportRouteGetDto;
            };
    private static final Converter<Flight, FlightGetDto>

            FLIGHT_TO_FLIGHT_GET_DTO_CONVERTER =
            (context) -> {

                Flight flight = context.getSource();
                FlightGetDto flightGetDto = FlightGetDto.builder()
                        .code(flight.getCode())
                        .date(flight.getDate())
                        .departureTime(flight.getDepartureTime())
                        .arrivalTime(flight.getArrivalTime())
                        .routeId(flight.getRoute().getId())
                        .airlineName(flight.getAirline().getName())
                        .build();

                System.out.println("FLIGHT_TO_FLIGHT_GET_DTO_CONVERTER: " + flightGetDto);

                return flightGetDto;
            };
    private static final Converter<FlightAddDto, Flight>

            FLIGHT_ADD_DTO_TO_FLIGHT_CONVERTER =
            (context) -> {

                FlightAddDto flightAddDto = context.getSource();
                Flight flight = Flight.builder()
                        .code(flightAddDto.getCode())
                        .date(flightAddDto.getDate())
                        .departureTime(flightAddDto.getDepartureTime())
                        .arrivalTime(flightAddDto.getArrivalTime())
                        .build();

                System.out.println("FLIGHT_ADD_DTO_TO_FLIGHT_CONVERTER: " + flight);

                return flight;
            };

    private static final Converter<TicketAddDto, Ticket>

            TICKET_ADD_DTO_TO_TICKET_CONVERTER =
            (context) -> {

                TicketAddDto ticketAddDto = context.getSource();
                Ticket ticket = Ticket.builder()
                        .price(ticketAddDto.getPrice())
                        .number(ticketAddDto.getNumber())
                        .status(ticketAddDto.getStatus())
                        .build();

                System.out.println("TICKET_ADD_DTO_TO_TICKET_CONVERTER: " + ticket);

                return ticket;
            };

    private static final Converter<Ticket, TicketGetDto>

            TICKET_TO_TICKET_GET_DTO_CONVERTER =
            (context) -> {

                Ticket ticket = context.getSource();
                TicketGetDto ticketGetDto = TicketGetDto.builder()
                        .id(ticket.getId())
                        .price(ticket.getPrice())
                        .number(ticket.getNumber())
                        .status(ticket.getStatus())
                        .build();

                System.out.println("TICKET_TO_TICKET_GET_DTO_CONVERTER: " + ticketGetDto);

                return ticketGetDto;
            };

    private static final Converter<CardAddDto, Card>

            CARD_ADD_DTO_TO_CARD_CONVERTER =
            (context) -> {

                CardAddDto cardAddDto = context.getSource();
                Card card = Card.builder()
                        .id(cardAddDto.getId())
                        .number(cardAddDto.getNumber())
                        .cvv(cardAddDto.getCvv())
                        .deadline(cardAddDto.getDeadline())
                        .holder(cardAddDto.getHolder())
                        .balance(cardAddDto.getBalance())
                        .build();

                System.out.println("CARD_ADD_DTO_TO_CARD_CONVERTER: " + card);

                return card;
            };

    private static final Converter<Purchase, PurchaseGetDto>

            PURCHASE_TO_PURCHASE_GET_DTO_CONVERTER =
            (context) -> {

                Purchase purchase = context.getSource();
                PurchaseGetDto purchaseGetDto = PurchaseGetDto.builder()
                        .purchaseId(purchase.getId())
                        .ticketId(purchase.getTicket().getId())
                        .purchaser(purchase.getCard().getHolder())
                        .cardNumber(purchase.getCard().getNumber())
                        .purchasedPrice(purchase.getPrice())
                        .cardRemainingBalance(purchase.getCard().getBalance())
                        .purchaseLocalDateTime(purchase.getDatetime())
                        .ticketNumber(purchase.getTicket().getNumber())
                        .ticketStatus(purchase.getTicket().getStatus())
                        .build();

                System.out.println("PURCHASE_TO_PURCHASE_GET_DTO_CONVERTER: " + purchaseGetDto);

                return purchaseGetDto;
            };


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(AIRLINE_ADD_DTO_TO_AIRLINE_CONVERTER, AirlineAddDto.class, Airline.class);
        modelMapper.addConverter(AIRLINE_TO_AIRLINE_GET_DTO_CONVERTER, Airline.class, AirlineGetDto.class);
        modelMapper.addConverter(AIRPORT_ADD_DTO_TO_AIRPORT_CONVERTER, AirportAddDto.class, Airport.class);
        modelMapper.addConverter(AIRPORT_TO_AIRPORT_GET_DTO_CONVERTER, Airport.class, AirportGetDto.class);
        modelMapper.addConverter(AIRPORT_ROUTE_TO_AIRPORT_ROUTE_GET_DTO_CONVERTER, AirportRoute.class, AirportRouteGetDto.class);
        modelMapper.addConverter(FLIGHT_TO_FLIGHT_GET_DTO_CONVERTER, Flight.class, FlightGetDto.class);
        modelMapper.addConverter(FLIGHT_ADD_DTO_TO_FLIGHT_CONVERTER, FlightAddDto.class, Flight.class);
        modelMapper.addConverter(TICKET_ADD_DTO_TO_TICKET_CONVERTER, TicketAddDto.class, Ticket.class);
        modelMapper.addConverter(TICKET_TO_TICKET_GET_DTO_CONVERTER, Ticket.class, TicketGetDto.class);
        modelMapper.addConverter(CARD_ADD_DTO_TO_CARD_CONVERTER, CardAddDto.class, Card.class);
        modelMapper.addConverter(PURCHASE_TO_PURCHASE_GET_DTO_CONVERTER, Purchase.class, PurchaseGetDto.class);

        return modelMapper;
    }
}
