package com.utn.dabd.tpi.blackjack.controllers;

import com.mysql.cj.util.StringUtils;
import com.utn.dabd.tpi.blackjack.dto.LoginRequestDTO;
import com.utn.dabd.tpi.blackjack.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tpi/blackjack/user")
@RequiredArgsConstructor
public class JugadorController {
    
    private final PlayerService playerService;
    private final ModelMapper modelMapper;
    
    @PostMapping("/login")
    ResponseEntity<com.utn.dabd.tpi.blackjack.dto.Player> login(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        String userName = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        
        if(StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password) ) {
            throw new IllegalArgumentException("Parametros no deben ser nulos");
        }
        
        com.utn.dabd.tpi.blackjack.entities.Player player = playerService.login(userName, password);
        com.utn.dabd.tpi.blackjack.dto.Player result = 
                modelMapper.map(player, com.utn.dabd.tpi.blackjack.dto.Player.class);
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping()
    ResponseEntity<Void> createUser(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        String userName = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        
        playerService.agregarPlayer(userName, password);
        
        return ResponseEntity.ok().build();
    }
}
