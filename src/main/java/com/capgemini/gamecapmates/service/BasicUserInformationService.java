package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.GamesHistory;
import com.capgemini.gamecapmates.domain.Statistics;
import com.capgemini.gamecapmates.enums.GameResult;
import com.capgemini.gamecapmates.enums.Level;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.StatisticsDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.StatisticsMapper;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.GamesHistoryRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.validation.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasicUserInformationService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private GamesHistoryRepository gamesHistoryRepository;
    private StatisticsMapper statisticsMapper;
    private UserValidator userValidator;

   private static Logger logger = Logger.getLogger(BasicUserInformationService.class);

    public BasicUserInformationService(UserRepository userRepository, UserMapper userMapper, GamesHistoryRepository gamesHistoryRepository, StatisticsMapper statisticsMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.gamesHistoryRepository = gamesHistoryRepository;
        this.statisticsMapper = statisticsMapper;
        this.userValidator= userValidator;
    }

    public UserDto addUser(UserDto userDto){
        User user= userMapper.mapDtoToEntity(userDto);
        return userMapper.mapEntityToDto(user);
    }

    /**
     *display user data with specific basic information
     * @param userDto object of user to display
     * @throws NoSuchUserException check if object or component of user is not null
     * */
    public UserDto userView(UserDto userDto) throws NoSuchUserException {
        userValidator.checkIfUserDtoComponentIsNull(userDto);
        userValidator.checkIfUserDtoIsNull(userDto);

        User user = userMapper.mapDtoToEntity(userDto);
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .motto(user.getMotto())
                .build();
    }
    /**
     *display user data with specific basic information
     * @param statisticsDto object od user statistic to display
     * @throws NoSuchUserException - check if user statistics component and object are not null
     * */
    public StatisticsDto statisticsView(StatisticsDto statisticsDto) throws  NoSuchUserException {
        userValidator.checkIfStatisticsUserIsNull(statisticsDto);
        userValidator.checkIfStatisticsComponentIsDifferentThanNull(statisticsDto);

        Statistics statistics = statisticsMapper.mapDtotoEntity(statisticsDto);
        return StatisticsDto.builder()
                .level(statistics.getLevel())
                .rankingPosition(statistics.getRankingPosition())
                .build();
    }
    /**
     *Edit and update user basic information
     * @param userUpdate user to update
     * @throws NoSuchUserException - check if user object to update Date component is right or if object is not null
     * */
    public UserDto updateUserBasicInformation(final UserUpdateDto userUpdate) throws NoSuchUserException {
        userValidator.checkIfUserUpdateIsNull(userUpdate);
        userValidator.checkIfUserUpdateBirthDateIsSmaller(userUpdate);

            User user = userMapper.mapUserUpdateDtoToUser(userUpdate);
            User updatedUser = userRepository.edit(user);

            return userMapper.mapEntityToDto(updatedUser);
    }
    /**
     *Search for User
     * @param user_id search for user with specific id
     * @throws NoSuchUserException - check if Id of User is not null
     * */
    public UserDto findUserById(Long user_id) throws NoSuchUserException {
        userValidator.checkIfUserIdIsNull(user_id);

        User user_Id = userRepository.findById(user_id);

        return userMapper.mapEntityToDto(user_Id);
    }
    /**
     *Search for all user in collection system
     *
     * */
    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();

        return userMapper.mapListToDto(users);
    }
    /**
     * Add user to system collection
     * @param userDto object to add
     * @throws NoSuchUserException- check if user object or component to add is not null
     * */
    public void addUserToRepository(UserDto userDto) throws NoSuchUserException {
        userValidator.checkIfUserDtoIsNull(userDto);
        userValidator.checkIfUserDtoComponentIsNull(userDto);

        User user = userMapper.mapDtoToEntity(userDto);
        userRepository.add(user);
    }
    /**
     *Remove user from repository
     * @param userDto object of user to remove
     * @throws NoSuchUserException - check if user object to remove is not null
     * */
    public void removeUser(UserDto userDto) throws NoSuchUserException {
        userValidator.checkIfUserDtoIsNull(userDto);

        User user = userMapper.mapDtoToEntity(userDto);
        if (userRepository.findAll().contains(userRepository.findById(userDto.getId()))) {
            userRepository.remove(user);
        } else {
            throw new NoSuchUserException();
        }
    }

    /**
     * Return user object statistics from repository
     * @param userId find user statistics by user id
     * @throws NoSuchUserException- check if user Id is not null, check if contains in repository
     * */
    // find by user_id if user statistics component are not null
    public StatisticsDto getUserStatistics(Long userId) throws NoSuchUserException {
        userValidator.checkIfUserIdIsNull(userId);

        if (userRepository.findAll().contains(userRepository.findById(userId))) {
            return StatisticsDto.builder()
                    .userId(userId)
                    .level(calculateLevel(userId))
                    .gameWin(numberOfWonGames(userId))
                    .gameLose(numberOfLoseGames(userId))
                    .gameDraw(numberOfDraw(userId))
                    .rankingPosition(getUserPositionInRanking(userId))
                    .build();
        } else {
            throw new NoSuchUserException();
        }
    }

    private Long getUserPositionInRanking(Long userId) throws NoSuchUserException {
        userValidator.checkIfUserIdIsNull(userId);
        return 1L;
    }

    private Level calculateLevel(Long userId) {
        try {
            long numberOfWon = numberOfWonGames(userId);
            if (numberOfWon > 0 && numberOfWon <= 10) {
                return Level.CAN_I_PLAY_DADDY;
            }
            if (numberOfWon > 10 && numberOfWon <= 30) {
                return Level.DO_NOT_HURT_ME;
            }
            if (numberOfWon > 30 && numberOfWon <= 60) {
                return Level.BRING_EM_ON;
            } else {
                return Level.I_AM_DEATH_INCARNATE;
            }
        } catch (NoSuchUserException e) {
            logger.error("There is no user with such name");
        }
        return null;
    }

    private List<GamesHistory> getGamesHistoryOfUser(Long userId) throws NoSuchUserException {
        List<Long> userHistory = userRepository.findById(userId).getUserGamesHistory();
        List<GamesHistory> allgamesHistory = gamesHistoryRepository.findAll();
        return allgamesHistory.stream()
                .filter(gamesHistory -> userHistory.contains(gamesHistory.getId()))
                .collect(Collectors.toList());
    }

    private long numberOfWonGames(Long userId) throws NoSuchUserException {
        List<GamesHistory> userGameHistory = getGamesHistoryOfUser(userId);
        return userGameHistory.stream()
                .filter(gamesHistory -> gamesHistory.getGameResult().equals(GameResult.WIN))
                .count();
    }

    private long numberOfDraw(Long userId) throws NoSuchUserException {
        List<GamesHistory> userGameHistory = getGamesHistoryOfUser(userId);
        return userGameHistory.stream()
                .filter(gamesHistory -> gamesHistory.getGameResult().equals(GameResult.DRAW))
                .count();
    }

    private long numberOfLoseGames(Long userId) throws NoSuchUserException {
        List<GamesHistory> userGameHistory = getGamesHistoryOfUser(userId);
        return userGameHistory.stream()
                .filter(gamesHistory -> gamesHistory.getGameResult().equals(GameResult.LOSE))
                .count();
    }
}
