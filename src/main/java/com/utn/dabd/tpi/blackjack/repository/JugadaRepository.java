package com.utn.dabd.tpi.blackjack.repository;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadaRepository extends JpaRepository<Jugada, Long> {
    
    Jugada findByIdPlayerAndResultado(Long idPlayer, Resultados Resultado); 
}
