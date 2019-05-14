package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/games")
public class BoardGamesRestController {

    private BoardGamesService boardGamesService;

    @Autowired
    public BoardGamesRestController( BoardGamesService boardGamesService) {
        this.boardGamesService = boardGamesService;
    }
    /**
     *          Method which handles HTTP GET request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.GameDto} object found by its id
     * @param id
     *          Long id of object {@link com.capgemini.gamecapmates.dto.GameDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.GameDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.Game} with given object is null
     */
    @GetMapping("/game/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long id) throws Exception {
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        GameDto gameDto = boardGamesService.getGameById(id);
        return ResponseEntity.ok().body(gameDto);
    }

    /**
     *          Method which handles HTTP POST request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.GameDto} object found by its id
     * @param gameDto
     *          object {@link com.capgemini.gamecapmates.dto.GameDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.GameDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.Game} with given object is null
     */
    @PostMapping(value= "/addGame", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto) throws Exception {
        GameDto game = boardGamesService.addGame(gameDto);
        return ResponseEntity.ok().body(game);
    }
}
