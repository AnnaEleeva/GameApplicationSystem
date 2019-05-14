package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.restService.BoardGamesWebService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class BoardGamesRestController {

    private BoardGamesService boardGamesService;

    @Autowired
    public BoardGamesRestController( BoardGamesService boardGamesService) {
        this.boardGamesService = boardGamesService;
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long id) throws NoSuchGameException {
        GameDto gameDto = boardGamesService.getGameById(id);
        return ResponseEntity.ok().body(gameDto);
    }

    @PostMapping(value= "/addGame", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto) throws Exception {
        GameDto game = boardGamesService.addGame(gameDto);
        return ResponseEntity.ok().body(game);
    }
}
