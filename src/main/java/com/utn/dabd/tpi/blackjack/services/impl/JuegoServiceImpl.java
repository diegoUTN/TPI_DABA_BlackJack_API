package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.dto.JugadaDTO;
import com.utn.dabd.tpi.blackjack.dto.TipoJugador;
import com.utn.dabd.tpi.blackjack.entities.Carta;
import com.utn.dabd.tpi.blackjack.entities.Jugada;
import com.utn.dabd.tpi.blackjack.entities.JugadaPorCarta;
import com.utn.dabd.tpi.blackjack.dto.Resultados;
import com.utn.dabd.tpi.blackjack.services.CartaService;
import com.utn.dabd.tpi.blackjack.services.JuegoService;
import com.utn.dabd.tpi.blackjack.services.JugadaPorCartaService;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import com.utn.dabd.tpi.blackjack.services.PlayerService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JuegoServiceImpl implements JuegoService{
    
    private final JugadaService jugadaService;
    private final JugadaPorCartaService jugadaPorCartaService;
    private final CartaService cartaService;
    private final PlayerService playerService; 
    
    @Transactional
    @Override
    public JugadaDTO iniciarJugada(Long userId) {
        // Valido que exista el id de usuario
        playerService.getPlayerById(userId);
        // obtener ultima jugada activa del jugador
        Jugada jugada = jugadaService.recuperarJugadaActiva(userId, 
                Resultados.EN_JUEGO);
        
        if(jugada == null) {
            //crear nueva jugada
            Jugada jugadaToCreate = new Jugada();
            jugadaToCreate.setFecha(LocalDate. now());
            jugadaToCreate.setIdPlayer(userId);
            jugadaToCreate.setResultado(Resultados.EN_JUEGO);
            
            jugada = jugadaService.crearJugada(jugadaToCreate);
            
            // agrego carta al groupier
            darCarta(jugada.getId(), TipoJugador.C);
            
            // agregar cartas al jugador
            darCarta(jugada.getId(), TipoJugador.J);
            darCarta(jugada.getId(), TipoJugador.J);
        }
        
        List<JugadaPorCarta> jugadaCartas = 
                jugadaPorCartaService.getAllCartasPorJugada(jugada.getId());
        
        return generarResponse(jugadaCartas, jugada.getId(), Resultados.EN_JUEGO);
    }
    
    @Transactional
    @Override
    public JugadaDTO pedirCarta(Long idJugada) {
        // agregar cartas al jugador
        darCarta(idJugada, TipoJugador.J);
        List<JugadaPorCarta> jugadaCartas = 
                jugadaPorCartaService.getAllCartasPorJugada(idJugada);
        
        List<Long> cartasJug = new ArrayList();
        
        for(JugadaPorCarta j : jugadaCartas) {
            if(j.getDe().equals(TipoJugador.J)) {
                cartasJug.add(j.getIdCarta());
            }
        }
        List<Carta> cartasJugador = cartaService.obtenerCartasPorIds(cartasJug);
        
        List<Integer> totalJug = getTotales(cartasJugador);
        
        if(this.seguirJugando(totalJug)) {
            return generarResponse(jugadaCartas, idJugada, Resultados.EN_JUEGO);
        } else {
            return this.rendirse(idJugada);
        }
    }
    
    @Transactional
    @Override
    public JugadaDTO rendirse(Long idJugada) {
        // pedir carta para Croupier
        this.pedirCartaCroupier(idJugada);
        
        List<JugadaPorCarta> jugadasFinal = 
                jugadaPorCartaService.getAllCartasPorJugada(idJugada);
        return finalizarJuego(jugadasFinal, idJugada);
        
    }
    
    private Long darCarta(Long idJugada, TipoJugador de) {
        boolean yaSalioCarta = true;
        Long carta = null;
        while(yaSalioCarta) {
            carta = (long)(Math.random()* 52 + 1);
            yaSalioCarta = jugadaPorCartaService.salioCartaEnJugada(idJugada, carta);
        }
        JugadaPorCarta jxc = new JugadaPorCarta();
        jxc.setIdJugada(idJugada);
        jxc.setIdCarta(carta);
        jxc.setDe(de);
        
        jugadaPorCartaService.guardarCartaPorJugada(jxc);
        return carta;
    }
    
    private void pedirCartaCroupier(Long idJugada) {
        darCarta(idJugada, TipoJugador.C);
        
        List<JugadaPorCarta> jugadaCartas = 
                jugadaPorCartaService.getAllCartasPorJugada(idJugada);
        
        if(this.croupierSiguePidiendo(jugadaCartas)) {
            this.pedirCartaCroupier(idJugada);
        }
    }
    
    private boolean seguirJugando(List<Integer> totales) {
        for(int total : totales) {
            if(total < 22) {
                return true;
            }
        }
        return false;
    }
    
    private boolean croupierSiguePidiendo(List<JugadaPorCarta> jugadas) {
        List<Long> cartasCr = new ArrayList();
        List<Long> cartasJug = new ArrayList();
        
        for(JugadaPorCarta j : jugadas) {
            if(j.getDe().equals(TipoJugador.C)) {
                cartasCr.add(j.getIdCarta());
            } else if(j.getDe().equals(TipoJugador.J)) {
                cartasJug.add(j.getIdCarta());
            }
        }
        List<Carta> cartasJugador = cartaService.obtenerCartasPorIds(cartasJug);
        List<Carta> cartasCroup = cartaService.obtenerCartasPorIds(cartasCr);
        
        int mejorTotalJug = getMejorJugada(cartasJugador);
        if(mejorTotalJug == 0 ) {
          return false;
        }
        int mejorTotalCr = getMejorJugada(cartasCroup);
        if(mejorTotalCr == 0) {
          return false;
        }
        return mejorTotalJug > mejorTotalCr;
    }
    
    private JugadaDTO finalizarJuego(List<JugadaPorCarta> jugadas, Long jugadaId) {
        List<Long> cartasCr = new ArrayList();
        List<Long> cartasJug = new ArrayList();
        
        for(JugadaPorCarta j : jugadas) {
            if(j.getDe().equals(TipoJugador.C)) {
                cartasCr.add(j.getIdCarta());
            } else if(j.getDe().equals(TipoJugador.J)) {
                cartasJug.add(j.getIdCarta());
            }
        }
        
        List<Carta> cartasJugador = cartaService.obtenerCartasPorIds(cartasJug);
        List<Carta> cartasCroup = cartaService.obtenerCartasPorIds(cartasCr);
        
        int totalFinalJug = getUltimoTotal(cartasJugador);
        int totalFinalCr = getUltimoTotal(cartasCroup);
        
        int mejorTotalJug = getMejorJugada(cartasJugador);
        int mejorTotalCr = getMejorJugada(cartasCroup);
        
        Resultados resultado = (mejorTotalJug > mejorTotalCr) 
                ? (Resultados.GANO) : Resultados.PERDIO;
        
        jugadaService.updateResultado(jugadas.get(0).getIdJugada(), 
                resultado, totalFinalCr, totalFinalJug);
        
        return generarResponse(jugadas, jugadaId, resultado);
    }
    
    private int getUltimoTotal(List<Carta> cartas) {
        List<Integer> totales = getTotales(cartas);
        
        return (totales.size() > 1 && totales.get(1) < 22) 
                ? totales.get(1) : totales.get(0);
    }
    
    private List<Integer> getTotales(List<Carta> cartas) {
        List<Integer> totList = new ArrayList();
        int total = 0;
        boolean tieneAs = false;
        for(Carta c : cartas) {
            total += c.getValor();
            if(c.getValor() == 1) {
                tieneAs = true;
            }
        }
        totList.add(total);
        if(tieneAs) {
            totList.add(total + 10);
        }
        return totList; 
    }
    
    private Integer getMejorJugada(List<Carta> cartas) {
        int mejor = 0;
        List<Integer> totales = this.getTotales(cartas);
        for(Integer t : totales) {
            if(t < 22 && t > mejor) {
                mejor = t;
            }
        }
        return mejor;
    }
     
     private JugadaDTO generarResponse(List<JugadaPorCarta> jugadas,
             Long idJugada, Resultados resultado) {
        List<Long> cartasCr = new ArrayList();
        List<Long> cartasJug = new ArrayList();
        
        for(JugadaPorCarta j : jugadas) {
            if(j.getDe().equals(TipoJugador.C)) {
                cartasCr.add(j.getIdCarta());
            } else if(j.getDe().equals(TipoJugador.J)) {
                cartasJug.add(j.getIdCarta());
            }
        }
        
        List<Carta> cartasJugador = cartaService.obtenerCartasPorIds(cartasJug);
        List<Carta> cartasCroup = cartaService.obtenerCartasPorIds(cartasCr);
        
        return JugadaDTO.builder()
                .id(idJugada)
                .resultado(resultado)
                .cartasCroupier(cartasCr)
                .cartasJugador(cartasJug)
                .totales(getTotales(cartasJugador))
                .totalCroupier(getTotales(cartasCroup))
                .build();
     }
    
}
