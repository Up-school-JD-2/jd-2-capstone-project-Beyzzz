package com.example.airlineticketsystem.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@ToString
public class AirportAddDto {

    @NotBlank
    private String code;

    @NotBlank
    private String name;


}
