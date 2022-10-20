package com.utn.dabd.tpi.blackjack.controllers;

import com.utn.dabd.tpi.blackjack.dto.JugadaDTO;
import com.utn.dabd.tpi.blackjack.services.JuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tpi/blackjack")
@RequiredArgsConstructor
public class JuegoController {
    
    private final JuegoService juegoService;
    
    @GetMapping("/iniciar/{userName}")
    ResponseEntity<JugadaDTO> iniciarJugada(@PathVariable String userName) {
        JugadaDTO inicial = juegoService.iniciarJugada(userName);
        
        return ResponseEntity.ok(inicial);
    }
    
    @GetMapping("/pedir/{id}")
    ResponseEntity<JugadaDTO> pedirCarta(@PathVariable Long id) {
        JugadaDTO juego = juegoService.pedirCarta(id);
        if(juego == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juego);
    }
    
    @GetMapping("/rendir/{id}")
    ResponseEntity<JugadaDTO> rendirse(@PathVariable Long id) {
        JugadaDTO juego = juegoService.rendirse(id);
        if(juego == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juego);
    }
    
}
