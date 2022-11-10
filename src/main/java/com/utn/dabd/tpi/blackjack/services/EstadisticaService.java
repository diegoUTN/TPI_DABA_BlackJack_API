package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import com.utn.dabd.tpi.blackjack.entities.EstadisticasGeneralesDTO;
import java.util.List;

public interface EstadisticaService {
    
    EstadisticasGeneralesDTO getEstadisticasGenerales();
    
    List<ReportDayDTO> getEstadisticasPorDia(int dia);
    
    EstadisticasGeneralesDTO getEstadisticasPorJugador(Long  userId);
}
