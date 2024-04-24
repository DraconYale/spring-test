package org.giangi.app.services;


import org.giangi.app.domain.Game;
import org.giangi.app.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> listAll() {
        return (List<Game>) gameRepository.findAll();
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    public Game get(Long id) {
        return gameRepository.findById(id).get();
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
