package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.dto.JugadaDTO;
import com.utn.dabd.tpi.blackjack.dto.TipoJugador;
import com.utn.dabd.tpi.blackjack.juego.Juego;
import com.utn.dabd.tpi.blackjack.model.Baraja;
import com.utn.dabd.tpi.blackjack.model.Carta;
import com.utn.dabd.tpi.blackjack.model.Jugada;
import com.utn.dabd.tpi.blackjack.model.Resultados;
import com.utn.dabd.tpi.blackjack.services.JuegoService;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JuegoServiceImpl implements JuegoService{
    
    private final Juego juego = new Juego();
    private final Baraja baraja = new Baraja();

    private final JugadaService jugadaService;
    
    @Override
    public JugadaDTO iniciarJugada(String userName) {
        // obtener ultima jugada activa del jugador
        Jugada jugada = obtenerJugadaActiva(userName);
        
        if(jugada == null) {
            //crear nueva jugada
            jugada = Jugada.builder()
                    .id((long)(Math.random()*999999 + 1))
                    .jugador(userName)
                    .resultado(Resultados.EN_JUEGO)
                    .cartasJugador(new ArrayList())
                    .cartasCroupier(new ArrayList())
                    .build();
            
            // agregar cartas al jugador
            Carta carta1Jug = pedirCarta(jugada);
            jugada.getCartasJugador().add(carta1Jug);
            
            Carta carta2Jug = pedirCarta(jugada);
            jugada.getCartasJugador().add(carta2Jug);
            
            // agrego carta al groupier
            List<Carta> cartasCr = new ArrayList();
            cartasCr.add(iniciarCroupier());
            jugada.setCartasCroupier(cartasCr);
        }
        return this.crearResponse(jugada);
    }
    
    @Override
    public JugadaDTO pedirCarta(Long idJugada) {
        Jugada jugada = getJugadaById(idJugada);
        if(jugada == null) {
            return null;
        }
        // agregar cartas al jugador
        Carta cartaJug = pedirCarta(jugada);
        jugada.getCartasJugador().add(cartaJug);
        
        List<Integer> totalJug = jugadaService.getTotales(jugada, TipoJugador.JUGADOR);
        
        if(this.seguirJugando(totalJug)) {
            return this.crearResponse(jugada);
        } else {
            juego.getJugadas().add(jugada);
            return this.rendirse(idJugada);
        }
    }
    
    @Override
    public JugadaDTO rendirse(Long idJugada) {
        Jugada jugada = getJugadaById(idJugada);
        if(jugada == null) {
            return null;
        }
        this.pedirCartaCroupier(jugada);
        
        return this.finalizarJuego(jugada);
    }
    
    private void pedirCartaCroupier(Jugada jugada) {
        Carta cartaCr = pedirCarta(jugada);
        jugada.getCartasCroupier().add(cartaCr);
        
        if(this.croupierSiguePidiendo(jugada)) {
            this.pedirCartaCroupier(jugada);
        }
    }
    
    private Jugada getJugadaById(Long id) {
        Jugada jugadaActiva = null;
        
        Iterator<Jugada> iter = juego.getJugadas().iterator();
        while(iter.hasNext()){
            Jugada j = iter.next();
            if(Objects.equals(j.getId(), id) 
                    && j.getResultado().equals(Resultados.EN_JUEGO)) {
                jugadaActiva = j;
                iter.remove();
            }
        }
        
        return jugadaActiva;
    }
    
    private Jugada obtenerJugadaActiva(String userName) {
        Jugada jugadaActiva = null;
        
        Iterator<Jugada> iter = juego.getJugadas().iterator();
        while(iter.hasNext()){
            Jugada j = iter.next();
            if(j.getJugador().equals(userName) 
                    && j.getResultado().equals(Resultados.EN_JUEGO)) {
                jugadaActiva = j;
                iter.remove();
            }
        }
        return jugadaActiva;
    }
    
    private Carta pedirCarta(Jugada jugada) {
        
        int numCarta = (int)(Math.random()* 52 + 1);
        if(estaRepetida(numCarta, jugada)) {
            pedirCarta(jugada);
        }
        return baraja.getCartas().get(numCarta - 1);
    }
    
    private boolean estaRepetida(int idCarta, Jugada jugada) {
        for(Carta c : jugada.getCartasCroupier()) {
            if(c.getId() == idCarta) {
                return true;
            }
        }
        for(Carta c : jugada.getCartasJugador()) {
            if(c.getId() == idCarta) {
                return true;
            }
        }
        return false;
    }
    
    private Carta iniciarCroupier() {
        int numCarta = (int)(Math.random()* 52 + 1);
        return baraja.getCartas().get(numCarta - 1);
    }
    
    private boolean seguirJugando(List<Integer> totales) {
        for(int total : totales) {
            if(total < 22) {
                return true;
            }
        }
        return false;
    }
    
    private boolean croupierSiguePidiendo(Jugada jugada) {
        int mejorTotalJug = jugadaService.getMejorJugada(jugada.getCartasJugador());
        if(mejorTotalJug == 0 ) {
          return false;
        }
        int mejorTotalCr = jugadaService.getMejorJugada(jugada.getCartasCroupier());
        if(mejorTotalCr == 0) {
          return false;
        }
        if(mejorTotalJug > mejorTotalCr) {
          return true;
        }
        return false;
    }
    
    private JugadaDTO finalizarJuego(Jugada jugada) {
        int mejorTotalJug = jugadaService.getMejorJugada(jugada.getCartasJugador());
        int mejorTotalCr = jugadaService.getMejorJugada(jugada.getCartasCroupier());
        if(mejorTotalJug > mejorTotalCr) {
            jugada.setResultado(Resultados.GANO);
            return this.crearResponse(jugada);
        } else {
            jugada.setResultado(Resultados.PERDIO);
            return this.crearResponse(jugada);
        }
    }
    
    private JugadaDTO crearResponse(Jugada jugada) {
        List<Integer> totalJug = jugadaService.getTotales(jugada, TipoJugador.JUGADOR);
        List<Integer> totalCrou = jugadaService.getTotales(jugada, TipoJugador.CROUPIER);
        
        juego.getJugadas().add(jugada);
        
        return JugadaDTO.builder()
                .id(jugada.getId())
                .cartasCroupier(jugada.getCartasCroupier().stream().map(c -> c.getId()).collect(Collectors.toList()))
                .cartasJugador(jugada.getCartasJugador().stream().map(c -> c.getId()).collect(Collectors.toList()))
                .jugador(jugada.getJugador())
                .resultado(jugada.getResultado())
                .totales(totalJug)
                .totalCroupier(totalCrou)
                .build();
    }
    
}
