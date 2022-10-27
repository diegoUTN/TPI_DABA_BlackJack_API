package com.utn.dabd.tpi.blackjack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "PALO")
public class Palo {
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
}
