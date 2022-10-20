
package com.utn.dabd.tpi.blackjack.juego;

import com.utn.dabd.tpi.blackjack.model.Jugada;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Juego {
    private List<Jugada> jugadas;
    
    public Juego() {
        jugadas = new ArrayList();
    }
}
