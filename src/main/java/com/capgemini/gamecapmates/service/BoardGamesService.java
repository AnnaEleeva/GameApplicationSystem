package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameBoardRepository;
import com.capgemini.gamecapmates.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardGamesService {

    private GameBoardRepository gameBoardRepository;
    private GameMapper gameMapper;
    private GameRepository gameRepository;

    public BoardGamesService(GameBoardRepository gameBoardRepository, GameMapper gameMapper, GameRepository gameRepository) {
        this.gameBoardRepository=gameBoardRepository;
        this.gameMapper=gameMapper;
        this.gameRepository= gameRepository;
    }
    public List<GameDto> getUserGames(Long userId){
      List<Game> userGames=  gameBoardRepository.findById(userId);

      return gameMapper.mapListToDto(userGames);
    }

    public GameDto addGameAlreadyExistsToUser(String name) throws NoSuchGameException {
        Optional<Game> gameName = gameRepository.findByName(name);

        gameRepository.save(gameName);

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
