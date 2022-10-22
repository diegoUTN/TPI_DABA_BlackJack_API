package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import com.utn.dabd.tpi.blackjack.repository.JugadaRepository;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JugadaServiceImpl implements JugadaService {
    
    private final JugadaRepository jugadaRepository;
    
    @Override
    public Jugada crearJugada(Jugada jugada) {
        return jugadaRepository.save(jugada);
    }
    
    @Override
    public Jugada obtenerJugadaPorId(Long id) {
        Optional<Jugada> opJugada = jugadaRepository.findById(id);
        if(opJugada.isPresent()) {
            return opJugada.get();
        }
        throw new EntityNotFoundException("No Existe Jugada: " + id);
    }
    
    @Override
    public Jugada recuperarJugadaActiva(Long playerId, Resultados resultado) {
        return jugadaRepository.findByIdPlayerAndResultado(playerId, resultado);
    }
    
    @Override
    public void updateResultado(Long id, Resultados resultado, int totalCr, int totalJu) {
        Jugada jugada = this.obtenerJugadaPorId(id);
        jugada.setResultado(resultado);
        jugada.setTotalCroupier(totalCr);
        jugada.setTotalJugador(totalJu);
        
        this.crearJugada(jugada);
    }
}
