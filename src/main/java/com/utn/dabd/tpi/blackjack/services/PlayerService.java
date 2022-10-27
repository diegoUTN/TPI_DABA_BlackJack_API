package com.utn.dabd.tpi.blackjack.services;

import com.utn.dabd.tpi.blackjack.entities.Player;

public interface PlayerService {
    
    Player agregarPlayer(String user, String password);
    
    Player login(String user, String password);
    
    Player getPlayerById(Long id);
}
