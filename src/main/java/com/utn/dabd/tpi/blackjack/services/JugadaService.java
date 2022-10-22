package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;

public interface JugadaService {
    
    Jugada crearJugada(Jugada jugada);
    
    Jugada obtenerJugadaPorId(Long id);
    
    Jugada recuperarJugadaActiva(Long playerId, Resultados resultado);
    
    void updateResultado(Long id, Resultados resultado, int totalCr, int totalJu);
}
