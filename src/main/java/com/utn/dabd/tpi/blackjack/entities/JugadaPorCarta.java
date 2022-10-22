package com.utn.dabd.tpi.blackjack.entities;

import com.utn.dabd.tpi.blackjack.dto.TipoJugador;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "JUGADA_X_CARTA")
public class JugadaPorCarta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_jugada")
    private Long idJugada;
    @Column(name = "id_carta")
    private Long idCarta;
    @Enumerated(EnumType.STRING)
    @Column(name = "de")
    private TipoJugador de;
    
}
