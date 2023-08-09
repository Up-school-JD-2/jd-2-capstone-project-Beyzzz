package com.example.airlineticketsystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
@Builder
@ToString
public class Flight {
    @Id
    @Column(name = "code")
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return code.equals(flight.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
