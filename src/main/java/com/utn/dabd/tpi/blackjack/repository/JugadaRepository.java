package com.utn.dabd.tpi.blackjack.repository;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import com.utn.dabd.tpi.blackjack.dto.reports.ReportCountDTO;
import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadaRepository extends JpaRepository<Jugada, Long> {
    
    Jugada findByIdPlayerAndResultado(Long idPlayer, Resultados Resultado);
    
    @Query(value = "SELECT j.resultado, count(*) as cantidad FROM " +
            "Jugada j group by j.resultado", nativeQuery = true)
    List<ReportCountDTO> countAllGames();
    
    @Query(value = "SELECT j.resultado, count(*) as cantidad FROM " 
            + "Jugada j WHERE id_player = :userId "
            + "GROUP BY j.resultado", nativeQuery = true)
    List<ReportCountDTO> countAllGamesUserID(Long userId);
    
    @Query(value = "SELECT count(*) from jugada WHERE total_jugador = 21",
            nativeQuery = true)
    Long countBlackJackJugador();
    
    @Query(value = "SELECT count(*) from jugada WHERE total_jugador = 21"
            + " AND id_player = :userId",
            nativeQuery = true)
    Long countBlackJackJugadorID(Long userId);
    
    @Query(value = "SELECT count(*) from jugada WHERE total_croupier = 21",
            nativeQuery = true)
    Long countBlackJackCroupier();
    
    @Query(value = "SELECT fecha, count(*) as jugadas, count(distinct id_player)"
            + " as jugadores FROM jugada where datediff(now(), fecha) < :days "
            + "GROUP BY fecha", nativeQuery = true)
    List<ReportDayDTO> getReportForDays(int days);
}
