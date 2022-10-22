package com.utn.dabd.tpi.blackjack.repository;

import com.utn.dabd.tpi.blackjack.entities.Carta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
    
    List<Carta> findByIdIn(List<Long> ids);
}
