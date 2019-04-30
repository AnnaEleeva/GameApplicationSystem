package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.dao.MainDao;
import com.capgemini.gamecapmates.domain.GameBoard;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.repository.GameBoardRepository;
import com.capgemini.gamecapmates.repository.GameRepository;

public class BoardGamesService extends MainDao<GameBoard> {
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

}
