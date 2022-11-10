package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import com.utn.dabd.tpi.blackjack.entities.EstadisticasGeneralesDTO;
import com.utn.dabd.tpi.blackjack.services.EstadisticaService;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService {
    
    private final JugadaService jugadaService;

    @Override
    public EstadisticasGeneralesDTO getEstadisticasGenerales() {
        return jugadaService.getResultadosTotales();
    }

    @Override
    public List<ReportDayDTO> getEstadisticasPorDia(int dia) {
        return jugadaService.getReportesPorDias(dia);
    }

    @Override
    public EstadisticasGeneralesDTO getEstadisticasPorJugador(Long userId) {
        return jugadaService.getResultadosPorJugador(userId);
    }
    
}
