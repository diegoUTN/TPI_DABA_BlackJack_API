package com.utn.dabd.tpi.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Baraja {
    private List<Carta> cartas;
    
    public Baraja() {
        cartas = new ArrayList();
        
        //PALOS
        Palo pc = new Palo("C", "Corazones");
        Palo pd = new Palo("D", "Diamantes");
        Palo pp = new Palo("P", "Picas");
        Palo pt = new Palo("T", "Trebol");
        
        //CARTAS
        Carta c1 = new Carta(1L, 1, pc, 1);
        Carta c2 = new Carta(2L, 1, pd, 1);
        Carta c3 = new Carta(3L, 1, pp, 1);
        Carta c4 = new Carta(4L, 1, pt, 1);
        
        Carta c5 = new Carta(5L, 2, pc, 2);
        Carta c6 = new Carta(6L, 2, pd, 2);
        Carta c7 = new Carta(7L, 2, pp, 2);
        Carta c8 = new Carta(8L, 2, pt, 2);
        
        Carta c9 = new Carta(9L, 3, pc, 3);
        Carta c10 = new Carta(10L, 3, pp, 3);
        Carta c11 = new Carta(11L, 3, pd, 3);
        Carta c12 = new Carta(12L, 3, pt, 3);
        
        Carta c13 = new Carta(13L, 4, pd, 4);
        Carta c14 = new Carta(14L, 4, pc, 4);
        Carta c15 = new Carta(15L, 4, pp, 4);
        Carta c16 = new Carta(16L, 4, pt, 4);
        
        Carta c17 = new Carta(17L, 5, pc, 5);
        Carta c18 = new Carta(18L, 5, pp, 5);
        Carta c19 = new Carta(19L, 5, pd, 5);
        Carta c20 = new Carta(20L, 5, pt, 5);
        
        Carta c21 = new Carta(21L, 6, pc, 6);
        Carta c22 = new Carta(22L, 6, pp, 6);
        Carta c23 = new Carta(23L, 6, pd, 6);
        Carta c24 = new Carta(24L, 6, pt, 6);
        
        Carta c25 = new Carta(25L, 7, pc, 7);
        Carta c26 = new Carta(26L, 7, pp, 7);
        Carta c27 = new Carta(27L, 7, pd, 7);
        Carta c28 = new Carta(28L, 7, pt, 7);
        
        Carta c29 = new Carta(29L, 8, pc, 8);
        Carta c30 = new Carta(30L, 8, pp, 8);
        Carta c31 = new Carta(31L, 8, pd, 8);
        Carta c32 = new Carta(32L, 8, pt, 8);
        
        Carta c33 = new Carta(33L, 9, pc, 9);
        Carta c34 = new Carta(34L, 9, pp, 9);
        Carta c35 = new Carta(35L, 9, pd, 9);
        Carta c36 = new Carta(36L, 9, pt, 9);
        
        Carta c37 = new Carta(37L, 10, pc, 10);
        Carta c38 = new Carta(38L, 10, pd, 10);
        Carta c39 = new Carta(39L, 10, pp, 10);
        Carta c40 = new Carta(40L, 10, pt, 10);
        
        Carta c41 = new Carta(41L, 11, pc, 10);
        Carta c42 = new Carta(42L, 11, pp, 10);
        Carta c43 = new Carta(43L, 11, pt, 10);
        Carta c44 = new Carta(44L, 11, pd, 10);
        
        Carta c45 = new Carta(45L, 12, pc, 10);
        Carta c46 = new Carta(46L, 12, pp, 10);
        Carta c47 = new Carta(47L, 12, pd, 10);
        Carta c48 = new Carta(48L, 12, pt, 10);
        
        Carta c49 = new Carta(49L, 13, pc, 10);
        Carta c50 = new Carta(50L, 13, pp, 10);
        Carta c51 = new Carta(51L, 13, pd, 10);
        Carta c52 = new Carta(52L, 13, pt, 10);
        
        //BARAJA
        cartas.add(c1);
        cartas.add(c2);
        cartas.add(c3);
        cartas.add(c4);
        cartas.add(c5);
        cartas.add(c6);
        cartas.add(c7);
        cartas.add(c8);
        cartas.add(c9);
        cartas.add(c10);
        cartas.add(c11);
        cartas.add(c12);
        cartas.add(c13);
        cartas.add(c14);
        cartas.add(c15);
        cartas.add(c16);
        cartas.add(c17);
        cartas.add(c18);
        cartas.add(c19);
        cartas.add(c20);
        cartas.add(c21);
        cartas.add(c22);
        cartas.add(c23);
        cartas.add(c24);
        cartas.add(c25);
        cartas.add(c26);
        cartas.add(c27);
        cartas.add(c28);
        cartas.add(c29);
        cartas.add(c30);
        cartas.add(c31);
        cartas.add(c32);
        cartas.add(c33);
        cartas.add(c34);
        cartas.add(c35);
        cartas.add(c36);
        cartas.add(c37);
        cartas.add(c38);
        cartas.add(c39);
        cartas.add(c40);
        cartas.add(c41);
        cartas.add(c42);
        cartas.add(c43);
        cartas.add(c44);
        cartas.add(c45);
        cartas.add(c46);
        cartas.add(c47);
        cartas.add(c48);
        cartas.add(c49);
        cartas.add(c50);
        cartas.add(c51);
        cartas.add(c52);
    }
    
}
