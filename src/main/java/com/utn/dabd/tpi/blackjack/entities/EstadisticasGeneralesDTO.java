package com.utn.dabd.tpi.blackjack.entities;

import lombok.Data;

@Data
public class EstadisticasGeneralesDTO {
    private Integer totalJugadas;
    private Integer ganadasCroupier;
    private Integer ganadasJugadores;
    private Long blackjackCroupier;
    private Long blackjackJugadores;
}
