package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.entities.Player;
import com.utn.dabd.tpi.blackjack.repository.PlayerRepository;
import com.utn.dabd.tpi.blackjack.services.PlayerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    
    @Override
    public Player agregarPlayer(String user, String password) {
        
        Player p = playerRepository.findByUsername(user);
        if(p != null) {
            throw new IllegalArgumentException("El username ya existe");
        }
        Player player = new Player();
        player.setUsername(user);
        player.setPassword(password);
        
        return playerRepository.save(player);
    }

    @Override
    public Player login(String user, String password) {
        Player player = playerRepository.findByUsernameAndPassword(user, password);
        if(player == null) {
            throw new IllegalArgumentException("Usuario y/o contrasena incorrecta");
        }
        return player;
    }

    @Override
    public Player getPlayerById(Long id) {
        Optional<Player> opPlayer = playerRepository.findById(id);
        if(opPlayer.isPresent()) {
            return opPlayer.get();
        }
        throw new IllegalArgumentException("No existe usuario con id " + id);
    }
    
}
