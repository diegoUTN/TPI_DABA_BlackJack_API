package com.utn.dabd.tpi.blackjack.services.impl;

import com.utn.dabd.tpi.blackjack.entities.Carta;
import com.utn.dabd.tpi.blackjack.repository.CartaRepository;
import com.utn.dabd.tpi.blackjack.services.CartaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartaServiceImpl implements CartaService {
    
    private final CartaRepository cartaRepository;

    @Override
    public List<Carta> obtenerCartasPorIds(List<Long> ids) {
        return cartaRepository.findByIdIn(ids);
    }
    
}
