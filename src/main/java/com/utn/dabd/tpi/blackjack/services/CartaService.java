package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.entities.Carta;
import java.util.List;

public interface CartaService {
    
    List<Carta> obtenerCartasPorIds(List<Long> ids);
}
