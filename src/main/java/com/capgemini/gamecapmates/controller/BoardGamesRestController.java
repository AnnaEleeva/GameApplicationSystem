package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.restService.BoardGamesWebService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class BoardGamesRestController {

    private BoardGamesWebService boardGamesWebService;
    private BoardGamesService boardGamesService;

    @Autowired
    public BoardGamesRestController(BoardGamesWebService boardGamesWebService, BoardGamesService boardGamesService) {
        this.boardGamesService = boardGamesService;
        this.boardGamesWebService = boardGamesWebService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long id) throws Exception {
        GameDto gameDto = boardGamesService.getGameById(id);
        return ResponseEntity.ok().body(gameDto);
    }

    @GetMapping("/allGames")
    public ResponseEntity<List<GameDto>> findAllGame() throws Exception {
        List<GameDto> allGames = boardGamesService.findAllGame();
        return ResponseEntity.ok().body(allGames);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<GameDto>> searchGame(@RequestBody GameDto gameDto) throws Exception {
        List<GameDto> findGames = boardGamesWebService.searchUser(gameDto);
        return ResponseEntity.ok().body(findGames);
    }
}
