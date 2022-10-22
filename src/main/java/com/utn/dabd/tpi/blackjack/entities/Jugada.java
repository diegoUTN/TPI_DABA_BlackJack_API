package com.utn.dabd.tpi.blackjack.entities;

import com.utn.dabd.tpi.blackjack.dto.Resultados;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "JUGADA")
public class Jugada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "id_player")
    private Long idPlayer;
    @Column(name = "total_jugador")
    private Integer totalJugador;
    @Column(name = "total_croupier")
    private Integer totalCroupier;
    @Enumerated(EnumType.STRING)
    private Resultados resultado;
}
