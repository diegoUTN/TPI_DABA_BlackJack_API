package com.utn.dabd.tpi.blackjack.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "CARTA")
public class Carta implements Serializable {
    @Id
    private Long id;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "cod_palo")
    private String codPalo;
    @Column(name = "valor")
    private Integer valor;
}
