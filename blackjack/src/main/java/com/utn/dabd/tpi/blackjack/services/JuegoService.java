package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.dto.JugadaDTO;

public interface JuegoService {
    
    JugadaDTO iniciarJugada(String userName);
    
    JugadaDTO pedirCarta(Long idJugada);
    
    JugadaDTO rendirse(Long idJugada);
    
}
