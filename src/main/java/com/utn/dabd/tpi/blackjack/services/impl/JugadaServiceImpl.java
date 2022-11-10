package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import com.utn.dabd.tpi.blackjack.dto.reports.ReportCountDTO;
import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import com.utn.dabd.tpi.blackjack.entities.EstadisticasGeneralesDTO;
import com.utn.dabd.tpi.blackjack.repository.JugadaRepository;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import java.util.List;
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

    @Override
    public EstadisticasGeneralesDTO getResultadosTotales() {
        EstadisticasGeneralesDTO result = new EstadisticasGeneralesDTO();
        List<ReportCountDTO> reports = jugadaRepository.countAllGames();
        
        for(ReportCountDTO rep : reports) {
           if(Resultados.GANO.name().equalsIgnoreCase(rep.getResultado())) {
               result.setGanadasJugadores(rep.getCantidad());
           } else if(Resultados.PERDIO.name().equalsIgnoreCase(rep.getResultado())) {
               result.setGanadasCroupier(rep.getCantidad());
           }
        }
        
        result.setTotalJugadas(
                result.getGanadasCroupier() + result.getGanadasJugadores());
        
        Long bjJugador = jugadaRepository.countBlackJackJugador();
        Long bjCroupier = jugadaRepository.countBlackJackCroupier();
        
        result.setBlackjackCroupier(bjCroupier);
        result.setBlackjackJugadores(bjJugador);
        
        return result;
    }

    @Override
    public List<ReportDayDTO> getReportesPorDias(int dias) {
        return jugadaRepository.getReportForDays(dias);
    }

    @Override
    public EstadisticasGeneralesDTO getResultadosPorJugador(Long userId) {
        EstadisticasGeneralesDTO result = new EstadisticasGeneralesDTO();
        List<ReportCountDTO> reports = 
                jugadaRepository.countAllGamesUserID(userId);
        
        for(ReportCountDTO rep : reports) {
           if(Resultados.GANO.name().equalsIgnoreCase(rep.getResultado())) {
               result.setGanadasJugadores(rep.getCantidad());
           } else if(Resultados.PERDIO.name().equalsIgnoreCase(rep.getResultado())) {
               result.setGanadasCroupier(rep.getCantidad());
           }
        }
        
        result.setTotalJugadas(
                result.getGanadasCroupier() + result.getGanadasJugadores());
        
        Long bjJugador = jugadaRepository.countBlackJackJugadorID(userId);
        
        result.setBlackjackJugadores(bjJugador);
        
        return result;
    }
    
}
