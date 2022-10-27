package com.utn.dabd.tpi.blackjack;

import com.utn.dabd.tpi.blackjack.entities.Carta;
import com.utn.dabd.tpi.blackjack.entities.Palo;
import com.utn.dabd.tpi.blackjack.repository.CartaRepository;
import com.utn.dabd.tpi.blackjack.repository.PaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackApplication implements CommandLineRunner {
    
    @Autowired
    CartaRepository cartaRepository;
    
    @Autowired
    PaloRepository paloRepository;

    public static void main(String[] args) {
	SpringApplication.run(BlackjackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        // #PALOS
        Palo palo1 = new Palo("C", "Corazones");
        paloRepository.save(palo1);
        Palo palo2 = new Palo("D", "Diamantes");
        paloRepository.save(palo2);
        Palo palo3 = new Palo("P", "Picas");
        paloRepository.save(palo3);
        Palo palo4 = new Palo("T", "Trebol");
        paloRepository.save(palo4);

        // # CARTAS
        Carta carta = new Carta(1L, 1, "C", 1);
        cartaRepository.save(carta);
        Carta carta2 = new Carta(2L, 1, "D", 1);
        cartaRepository.save(carta2);
        Carta carta3 = new Carta(3L, 1, "P", 1);
        cartaRepository.save(carta3);
        Carta carta4 = new Carta(4L, 1, "T", 1);
        cartaRepository.save(carta4);

        Carta carta5 = new Carta(5L, 2, "C", 2);
        cartaRepository.save(carta5);
        Carta carta6 = new Carta(6L, 2, "D", 2);
        cartaRepository.save(carta6);
        Carta carta7 = new Carta(7L, 2, "P", 2);
        cartaRepository.save(carta7);
        Carta carta8 = new Carta(8L, 2, "T", 2);
        cartaRepository.save(carta8);
        
        Carta carta9 = new Carta(9L, 3, "C", 3);
        cartaRepository.save(carta9);
        Carta carta10 = new Carta(10L, 3, "P", 3);
        cartaRepository.save(carta10);
        Carta carta11 = new Carta(11L, 3, "D", 3);
        cartaRepository.save(carta11);
        Carta carta12 = new Carta(12L, 3, "T", 3);
        cartaRepository.save(carta12);
        
        Carta carta13 = new Carta(13L, 4, "D", 4);
        cartaRepository.save(carta13);
        Carta carta14 = new Carta(14L, 4, "C", 4);
        cartaRepository.save(carta14);
        Carta carta15 = new Carta(15L, 4, "P", 4);
        cartaRepository.save(carta15);
        Carta carta16 = new Carta(16L, 4, "T", 4);
        cartaRepository.save(carta16);
        
        Carta carta17 = new Carta(17L, 5, "C", 5);
        cartaRepository.save(carta17);
        Carta carta18 = new Carta(18L, 5, "P", 5);
        cartaRepository.save(carta18);
        Carta carta19 = new Carta(19L, 5, "D", 5);
        cartaRepository.save(carta19);
        Carta carta20 = new Carta(20L, 5, "T", 5);
        cartaRepository.save(carta20);
        
        Carta carta21 = new Carta(21L, 6, "C", 6);
        cartaRepository.save(carta21);
        Carta carta22 = new Carta(22L, 6, "P", 6);
        cartaRepository.save(carta22);
        Carta carta23 = new Carta(23L, 6, "D", 6);
        cartaRepository.save(carta23);
        Carta carta24 = new Carta(24L, 6, "T", 6);
        cartaRepository.save(carta24);
        
        Carta carta25 = new Carta(25L, 7, "C", 7);
        cartaRepository.save(carta25);
        Carta carta26 = new Carta(26L, 7, "P", 7);
        cartaRepository.save(carta26);
        Carta carta27 = new Carta(27L, 7, "D", 7);
        cartaRepository.save(carta27);
        Carta carta28 = new Carta(28L, 7, "T", 7);
        cartaRepository.save(carta28);
        
        Carta carta29 = new Carta(29L, 8, "C", 8);
        cartaRepository.save(carta29);
        Carta carta30 = new Carta(30L, 8, "P", 8);
        cartaRepository.save(carta30);
        Carta carta31 = new Carta(31L, 8, "D", 8);
        cartaRepository.save(carta31);
        Carta carta32 = new Carta(32L, 8, "T", 8);
        cartaRepository.save(carta32);
        
        Carta carta33 = new Carta(33L, 9, "C", 9);
        cartaRepository.save(carta33);
        Carta carta34 = new Carta(34L, 9, "P", 9);
        cartaRepository.save(carta34);
        Carta carta35 = new Carta(35L, 9, "D", 9);
        cartaRepository.save(carta35);
        Carta carta36 = new Carta(36L, 9, "T", 9);
        cartaRepository.save(carta36);
        
        Carta carta37 = new Carta(37L, 10, "C", 10);
        cartaRepository.save(carta37);
        Carta carta38 = new Carta(38L, 10, "D", 10);
        cartaRepository.save(carta38);
        Carta carta39 = new Carta(39L, 10, "P", 10);
        cartaRepository.save(carta39);
        Carta carta40 = new Carta(40L, 10, "T", 10);
        cartaRepository.save(carta40);
        
        Carta carta41 = new Carta(41L, 11, "C", 10);
        cartaRepository.save(carta41);
        Carta carta42 = new Carta(42L, 11, "D", 10);
        cartaRepository.save(carta42);
        Carta carta43 = new Carta(43L, 11, "P", 10);
        cartaRepository.save(carta43);
        Carta carta44 = new Carta(44L, 11, "T", 10);
        cartaRepository.save(carta44);
	
        Carta carta45 = new Carta(45L, 12, "C", 10);
        cartaRepository.save(carta45);
        Carta carta46 = new Carta(46L, 12, "P", 10);
        cartaRepository.save(carta46);
        Carta carta47 = new Carta(47L, 12, "D", 10);
        cartaRepository.save(carta47);
        Carta carta48 = new Carta(48L, 12, "T", 10);
        cartaRepository.save(carta48);
        
        Carta carta49 = new Carta(49L, 13, "C", 10);
        cartaRepository.save(carta49);
        Carta carta50 = new Carta(50L, 13, "P", 10);
        cartaRepository.save(carta50);
        Carta carta51 = new Carta(51L, 13, "D", 10);
        cartaRepository.save(carta51);
        Carta carta52 = new Carta(52L, 13, "T", 10);
        cartaRepository.save(carta52);
    }

}
