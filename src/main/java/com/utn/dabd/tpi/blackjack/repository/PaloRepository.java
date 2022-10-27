package com.utn.dabd.tpi.blackjack.repository;

import com.utn.dabd.tpi.blackjack.entities.Palo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaloRepository extends JpaRepository<Palo, Long> {
    
}
