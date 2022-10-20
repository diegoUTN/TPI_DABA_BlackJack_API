package com.utn.dabd.tpi.blackjack.dto;

import com.utn.dabd.tpi.blackjack.model.Carta;
import com.utn.dabd.tpi.blackjack.model.Resultados;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JugadaDTO {
    private Long id;
    private String jugador;
    private List<Long> cartasJugador;
    private List<Long> cartasCroupier;
    private List<Integer> totales;
    private List<Integer> totalCroupier;
    private Resultados resultado;
}
