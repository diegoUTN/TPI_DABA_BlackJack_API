package com.utn.dabd.tpi.blackjack.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jugada {
    private Long id;
    private String jugador;
    private List<Carta> cartasJugador;
    private List<Carta> cartasCroupier;
    private Resultados resultado;
}
