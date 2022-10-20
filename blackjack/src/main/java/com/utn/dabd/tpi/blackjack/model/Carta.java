package com.utn.dabd.tpi.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carta {
    private Long id;
    private Integer numero;
    private Palo palo;
    private Integer valor;
}
