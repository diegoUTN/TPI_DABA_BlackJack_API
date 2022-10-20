/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.dto.TipoJugador;
import com.utn.dabd.tpi.blackjack.model.Carta;
import com.utn.dabd.tpi.blackjack.model.Jugada;
import com.utn.dabd.tpi.blackjack.services.JugadaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JugadaServiceImpl implements JugadaService {

    @Override
    public List<Integer> getTotales(Jugada jugada, TipoJugador tipo) {
        List<Carta> cartas = (tipo.equals(TipoJugador.JUGADOR)) 
                ? jugada.getCartasJugador() : jugada.getCartasCroupier();
        return getTotales(cartas);
    }
    
    @Override
    public Integer getMejorJugada(List<Carta> cartas) {
        int mejor = 0;
        List<Integer> totales = this.getTotales(cartas);
        for(Integer t : totales) {
            if(t < 22 && t > mejor) {
                mejor = t;
            }
        }
        return mejor;
    }
    
    
    private List<Integer> getTotales(List<Carta> cartas) {
        int total = 0;
        List<Integer> totList = new ArrayList();
        for(Carta c : cartas) {
            total += c.getValor();
        }
        totList.add(total);
        if(tieneAs(cartas)) {
            totList.add(total + 10);
        }
        return totList;
    }
    
    private boolean tieneAs(List<Carta> cartas) {
        for(Carta c : cartas) {
            if(c.getValor() == 1) {
                return true;
            }
        }
        return false;
    }
}
