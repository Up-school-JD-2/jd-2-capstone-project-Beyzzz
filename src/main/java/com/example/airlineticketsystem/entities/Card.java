package com.example.airlineticketsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cards",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"holder", "number"})
        })
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
@Builder
@ToString
public class Card {
    @Id
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private LocalDate deadline;
    @Column(nullable = false)
    private String holder;
    @Column(nullable = false)
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number.equals(card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
