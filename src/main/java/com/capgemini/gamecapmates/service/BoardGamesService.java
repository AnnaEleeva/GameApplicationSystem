package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardGamesService {

    private GameBoardRepository gameBoardRepository;
    private GameMapper gameMapper;

    public BoardGamesService(GameBoardRepository gameBoardRepository, GameMapper gameMapper) {
        this.gameBoardRepository=gameBoardRepository;
        this.gameMapper=gameMapper;
    }
    public List<GameDto> getUserGames(Long userId){
      //  gameBoardRepository.findById()

        return null;
    }

    public GameDto addGameAlreadyExistsToUser(String name) {
        return null;
    }

    public GameDto addGameThatIsNotExistsToUser(Long userId, GameDto gameDto) { // validate
        return null;
    }

    public GameDto findGameByName(String name){
        return null;
    }

    public List<GameDto> findAllGameFromCollection(){
        return null;
    }

    public GameDto removeGameFromCollection(){
        return null;
    }

    // metoda pobieranie wszystkich gier

}
