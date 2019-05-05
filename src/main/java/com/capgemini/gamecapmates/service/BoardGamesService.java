package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardGamesService {

    private static final Logger logger = Logger.getLogger(BoardGamesService.class);

    private GameMapper gameMapper;
   private GameRepository gameRepository;
    private UserRepository userRepository;

    public BoardGamesService(GameMapper gameMapper, GameRepository gameRepository, UserRepository userRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<GameDto> findAllGameFromCollection() {
        List<Game> games = gameRepository.findAll();
        return gameMapper.mapListToDto(games);
    }

    public GameDto getGameById(Long gameId) {
        try {
            Game game = gameRepository.findById(gameId);
            return gameMapper.mapEntityToDto(game);
        } catch (NoSuchGameException e) {
            logger.error("The game is not exist in your collection");
        }
        return null;
    }

    public List<GameDto> getUserGames(Long userId) {
        try {
            List<Game> userGames = userRepository.findById(userId).getUserGames();
            return gameMapper.mapListToDto(userGames);
        } catch (NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
        return null;
    }

    public void addGameAlreadyExistsToUser(Long userId, Long gameId) {
        try {
            if (gameRepository.findAll().contains(gameRepository.findById(gameId)))
                if (userRepository.findAll().contains(userRepository.findById(userId))) {
                    User user = userRepository.findById(userId);
                    Game newGame = gameRepository.findById(gameId);
                    user.getUserGames().add(newGame);
                }
        } catch (NoSuchGameException e) {
            logger.info("Game you trying add is not exist in collection. Try add new Game");
        } catch (NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
    }

    public void addGameThatIsNotExistsToUser(Long userId, GameDto gameDto) throws NoSuchGameException {
        if (!gameRepository.findAll().contains(gameDto)) {
            Game newGame = gameMapper.mapDtoToEntity(gameDto);
            gameRepository.add(newGame);
        } else {
            throw new NoSuchGameException();
        }
        addGameAlreadyExistsToUser(userId, gameDto.getId());
    }


    public void removeGameFromCollection(Long gameId) {
        try {
            if (gameRepository.findAll().contains(gameRepository.findById(gameId))) {
                gameRepository.remove(gameRepository.findById(gameId));
            }
        } catch (NoSuchGameException e) {
            logger.error("There is no user with such name");
        }
    }
}
