package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.entities.JugadaPorCarta;
import java.util.List;

public interface JugadaPorCartaService {
    
    List<JugadaPorCarta> getAllCartasPorJugada(Long jugadaId);
    
    void guardarCartaPorJugada(JugadaPorCarta jugadaPorCarta);
    
    boolean salioCartaEnJugada(Long idJugada, Long idCarta);
}
