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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGamesService {

    private static final Logger logger = Logger.getLogger(BoardGamesService.class);

    private GameMapper gameMapper;
    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardGamesService(GameMapper gameMapper, GameRepository gameRepository, UserRepository userRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<GameDto> findAllGame() {
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

    public List<GameDto> getUserGamesCollection(Long userId) {
        try {
            List<Long> userGamesId = userRepository.findById(userId).getUserGames();
            List<Game> games = gameRepository.findAll();
            List<Game> userGames = games.stream()
                    .filter(game -> userGamesId.contains(game.getId()))
                    .collect(Collectors.toList());
            return gameMapper.mapListToDto(userGames);
        } catch (NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
        return null;
    }

    public void removeGameFromUserCollection(Long userId, Long gameId) {
        try {
            if (gameRepository.findAll().contains(gameRepository.findById(gameId))
                    && userRepository.findAll().contains(userRepository.findById(userId))) {
                List<Long> userGamesId = userRepository.findById(userId).getUserGames();
                userGamesId.remove(gameId);
            }
        } catch (NoSuchGameException | NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
    }

    public void addGameAlreadyExistsToUser(Long userId, Long gameId) {
        try {
            if (gameRepository.findAll().contains(gameRepository.findById(gameId)))
                if (userRepository.findAll().contains(userRepository.findById(userId))) {
                    User user = userRepository.findById(userId);
                    Game newGame = gameRepository.findById(gameId);
                    user.getUserGames().add(newGame.getId());
                }
        } catch (NoSuchGameException e) {
            logger.info("Game you trying add is not exist in collection. Try add new Game");
        } catch (NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
    }

    public void addGameThatIsNotExistsInCollection(Long userId, GameDto gameDto) throws NoSuchGameException {
        Game game = gameMapper.mapDtoToEntity(gameDto);
        if (!gameRepository.findAll().contains(game)) {
            Game newGame = gameMapper.mapDtoToEntity(gameDto);
            gameRepository.add(newGame);
        } else {
            throw new NoSuchGameException();
        }
        addGameAlreadyExistsToUser(userId, gameDto.getId());
    }
}
