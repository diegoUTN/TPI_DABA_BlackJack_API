package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import com.utn.dabd.tpi.blackjack.entities.EstadisticasGeneralesDTO;
import java.util.List;

public interface JugadaService {
    
    Jugada crearJugada(Jugada jugada);
    
    Jugada obtenerJugadaPorId(Long id);
    
    Jugada recuperarJugadaActiva(Long playerId, Resultados resultado);
    
    void updateResultado(Long id, Resultados resultado, int totalCr, int totalJu);
    
    EstadisticasGeneralesDTO getResultadosTotales();
    
    EstadisticasGeneralesDTO getResultadosPorJugador(Long userId);
    
    List<ReportDayDTO> getReportesPorDias(int dias);
}
