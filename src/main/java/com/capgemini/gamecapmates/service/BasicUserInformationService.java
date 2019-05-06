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

    static Logger logger = Logger.getLogger(BasicUserInformationService.class);

    @Autowired
    public BasicUserInformationService(UserRepository userRepository, UserMapper userMapper, GamesHistoryRepository gamesHistoryRepository, StatisticsMapper statisticsMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.gamesHistoryRepository = gamesHistoryRepository;
        this.statisticsMapper = statisticsMapper;
    }

    public UserDto userView(UserDto userDto) throws NoSuchUserException {
        User user = userMapper.mapDtoToEntity(userDto);
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .motto(user.getMotto())
                .build();
    }

    public StatisticsDto statisticsView(StatisticsDto statisticsDto) {
        Statistics statistics = statisticsMapper.mapDtotoEntity(statisticsDto);
        return StatisticsDto.builder()
                .level(statistics.getLevel())
                .rankingPosition(statistics.getRankingPosition())
                .build();
    }

    public UserDto updateUserBasicInformation(final UserUpdateDto userUpdate) throws NoSuchUserException {
        if (userUpdate != null) {
            User user = userMapper.mapUserUpdateDtoToUser(userUpdate);
            User updatedUser = userRepository.edit(user);

            return userMapper.mapEntityToDto(updatedUser);
        } else {
            throw new NoSuchUserException();
        }
    }

    public UserDto findUserById(Long user_id) throws NoSuchUserException {
        User user_Id = userRepository.findById(user_id);

        return userMapper.mapEntityToDto(user_Id);
    }

    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();

        return userMapper.mapListToDto(users);
    }

    public void addUserToRepository(UserDto userDto) throws NoSuchUserException {
        User user = userMapper.mapDtoToEntity(userDto);
        userRepository.add(user);
    }

    public void removeUser(UserDto userDto) throws NoSuchUserException {
        User user = userMapper.mapDtoToEntity(userDto);
        userRepository.remove(user);
    }

    public StatisticsDto getUserStatistics(Long userId) throws NoSuchUserException {
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

    private Long getUserPositionInRanking(Long userid) throws NoSuchUserException {
        Long maxGamesWinByUsers = gamesHistoryRepository.findAll().stream()
                .filter(gamesHistory -> gamesHistory.getGameResult().equals(GameResult.WIN))
                .count();
        // sort ?
        return null;
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
