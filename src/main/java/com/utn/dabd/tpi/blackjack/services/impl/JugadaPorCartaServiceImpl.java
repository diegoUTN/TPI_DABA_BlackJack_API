package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.entities.JugadaPorCarta;
import com.utn.dabd.tpi.blackjack.repository.JugadaPorCartaRepository;
import com.utn.dabd.tpi.blackjack.services.JugadaPorCartaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JugadaPorCartaServiceImpl implements JugadaPorCartaService {
    
    private final JugadaPorCartaRepository jugagaPorCartaRepository;

    @Override
    public List<JugadaPorCarta> getAllCartasPorJugada(Long jugadaId) {
        return jugagaPorCartaRepository.findAllByIdJugada(jugadaId);
    }

    @Override
    public void guardarCartaPorJugada(JugadaPorCarta jugadaPorCarta) {
        jugagaPorCartaRepository.save(jugadaPorCarta);
    }

    @Override
    public boolean salioCartaEnJugada(Long idJugada, Long idCarta) {
        JugadaPorCarta jug = jugagaPorCartaRepository.findByIdJugadaAndIdCarta(idJugada, idCarta);
        
        return jug != null;
    }
    
}
