package org.giangi.app.controllers;

import org.giangi.app.domain.Game;
import org.giangi.app.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<Game> getGames(){
        List<Game> games = new ArrayList<>();
        gameService.listAll().forEach(games::add);
        return games;
    }

    @PostMapping("/games/new")
    public void addGame(@RequestBody Game game){
        gameService.save(game);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<?> updateGame(@RequestBody Game game, @PathVariable Long id){
        try {
            Game toUp = gameService.get(id);
            gameService.save(game);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("games/{id}")
    public void deleteGame(@PathVariable Long id){
        gameService.delete(id);
    }

}
