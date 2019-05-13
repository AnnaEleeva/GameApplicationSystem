package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.validation.GamesValidator;
import com.capgemini.gamecapmates.validation.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGamesService {

    // rest of Validation

    private static final Logger logger = Logger.getLogger(BoardGamesService.class);

    private GameMapper gameMapper;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private UserValidator userValidator;
    private GamesValidator gamesValidator;

    @Autowired
    public BoardGamesService(GameMapper gameMapper, GameRepository gameRepository, UserRepository userRepository, UserValidator userValidator, GamesValidator gamesValidator) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.gamesValidator = gamesValidator;
    }

    public List<GameDto> findAllGame() {
        List<Game> games = gameRepository.findAll();
        return gameMapper.mapListToDto(games);
    }

    public GameDto getGameById(Long gameId) throws NoSuchGameException {
        gamesValidator.checkIfUserGameIdIsNull(gameId);
        if (gameRepository.findAll().contains(gameRepository.findById(gameId))) {
            Game game = gameRepository.findById(gameId);
            return gameMapper.mapEntityToDto(game);
        }
        throw new NoSuchGameException();
    }

    /**
     * Gets all collection of Games belongs to user
     *
     * @param userId
     * @return all Games belongs to user by id
     */
    public List<GameDto> getUserGamesCollection(Long userId) throws NoSuchUserException {
        userValidator.checkIfUserIdIsNull(userId);
        if (userRepository.findAll().contains(userRepository.findById(userId))) {
            List<Long> userGamesId = userRepository.findById(userId).getUserGames();
            List<Game> games = gameRepository.findAll();
            List<Game> userGames = games.stream()
                    .filter(game -> userGamesId.contains(game.getId()))
                    .collect(Collectors.toList());
            return gameMapper.mapListToDto(userGames);
        }
        throw new NoSuchUserException();
    }

    /**
     * Remove Game by Id from user collection
     *
     * @param userId,
     * @param gameId
     * @throws NoSuchGameException if game object is not null or is not exists in system collection',
     * @throws NoSuchUserException if user object is not null or is not exists in system collection'
     */
    public void removeGameFromUserCollection(Long userId, Long gameId) throws NoSuchUserException, NoSuchGameException {
        userValidator.checkIfUserIdIsNull(userId);
        gamesValidator.checkIfUserGameIdIsNull(gameId);
        if (gameRepository.findAll().contains(gameRepository.findById(gameId))
                && userRepository.findAll().contains(userRepository.findById(userId))) {
            List<Long> userGamesId = userRepository.findById(userId).getUserGames();
            userGamesId.remove(gameId);
        } else {
            throw new NoSuchGameException();
        }

    }

    /**
     * Add Game to User collection that is already exists in system Collection
     *
     * @param userId,
     * @param gameId
     * @throws NoSuchGameException if game object is null,
     * @throws NoSuchUserException if user object is not exists in system collection or is not null
     */
    public void addGameAlreadyExistsToUser(Long userId, Long gameId) throws NoSuchUserException, NoSuchGameException {
        if (gameRepository.findAll().contains(gameRepository.findById(gameId)))
            if (userRepository.findAll().contains(userRepository.findById(userId))) {
                User user = userRepository.findById(userId);
                Game newGame = gameRepository.findById(gameId);
                user.getUserGames().add(newGame.getId());
            }
    }

    /**
     * Add Game to User Collection games with adding new Game to system Collection.
     * Only user can add game
     *
     * @param gameDto search for game object and check if exists
     * @throws NoSuchGameException if game object is not exists in system collection or is not null,
     * @throws NoSuchUserException if user object is not exists in system collection or is not null
     */
    public void addGameThatIsNotExistsToCollection(Long userId, GameDto gameDto) throws NoSuchGameException, NoSuchUserException {
        Game game = gameMapper.mapDtoToEntity(gameDto);
        if (userRepository.findAll().contains(userRepository.findById(userId)))
            if (!gameRepository.findAll().contains(game)) {
                Game newGame = gameMapper.mapDtoToEntity(gameDto);
                gameRepository.add(newGame);
            } else {
                throw new NoSuchGameException();
            }
        addGameAlreadyExistsToUser(userId, gameDto.getId());
    }
}
