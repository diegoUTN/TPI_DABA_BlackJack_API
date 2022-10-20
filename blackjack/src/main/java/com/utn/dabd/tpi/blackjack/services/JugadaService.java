package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.dto.TipoJugador;
import com.utn.dabd.tpi.blackjack.model.Carta;
import com.utn.dabd.tpi.blackjack.model.Jugada;
import java.util.List;

public interface JugadaService {
    
    List<Integer> getTotales(Jugada jugada, TipoJugador tipo);
    
    Integer getMejorJugada(List<Carta> cartas);
}
