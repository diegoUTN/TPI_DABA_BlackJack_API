package com.utn.dabd.tpi.blackjack.repository;

import com.utn.dabd.tpi.blackjack.entities.JugadaPorCarta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadaPorCartaRepository 
        extends JpaRepository<JugadaPorCarta, Long> {
    
    List<JugadaPorCarta> findAllByIdJugada(Long idJugada);
    
    JugadaPorCarta findByIdJugadaAndIdCarta(Long idJugada, Long idCarta);
}
