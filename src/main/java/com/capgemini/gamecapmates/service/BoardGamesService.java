package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.repository.GameBoardRepository;

public class BoardGamesService {
    // logika operujaca na dto

    private GameBoardRepository gameBoardRepository;

    public BoardGamesService(GameBoardRepository gameBoardRepository) {
        this.gameBoardRepository=gameBoardRepository;
    }

    public GameDto addGameAlreadyExists(String name) {
        return null;
    }

    public GameDto addGameThatIsNotExists() {
        return null;
    }

    // metoda pobieranie wszystkich gier

}
