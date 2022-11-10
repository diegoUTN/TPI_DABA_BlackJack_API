package com.utn.dabd.tpi.blackjack.controllers;

import com.utn.dabd.tpi.blackjack.dto.reports.ReportDayDTO;
import com.utn.dabd.tpi.blackjack.entities.EstadisticasGeneralesDTO;
import com.utn.dabd.tpi.blackjack.services.EstadisticaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tpi/blackjack/reporte")
@RequiredArgsConstructor
public class EstadisticasController {
    
    private final EstadisticaService estadisticaService;
    
    @GetMapping()
    ResponseEntity<EstadisticasGeneralesDTO> getGralReport() {
        EstadisticasGeneralesDTO estad = 
                estadisticaService.getEstadisticasGenerales();
        
        return ResponseEntity.ok(estad);
    }
    
    @GetMapping("/user/{userId}")
    ResponseEntity<EstadisticasGeneralesDTO> getReportePorUsuario(
            @PathVariable Long userId) {
        EstadisticasGeneralesDTO estad = 
                estadisticaService.getEstadisticasPorJugador(userId);
        
        return ResponseEntity.ok(estad);
    }
    
    @GetMapping("/{tipo}")
    ResponseEntity<List<ReportDayDTO>> getEstadisticasPorDia(@PathVariable String tipo) {
        int dias = 0;
        if("SEMANA".equalsIgnoreCase(tipo)) {
            dias = 7;
        } else if("MES".equalsIgnoreCase(tipo)) {
            dias = 30;
        } else {
            throw new IllegalArgumentException("Incorrecto parametro type");
        }
        List<ReportDayDTO> estad = estadisticaService.getEstadisticasPorDia(dias);
        
        return ResponseEntity.ok(estad);
    }
}
