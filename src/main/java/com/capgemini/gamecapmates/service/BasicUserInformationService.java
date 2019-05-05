package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.GameResult;
import com.capgemini.gamecapmates.domain.Level;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.RankingPositionDto;
import com.capgemini.gamecapmates.dto.StatisticsDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicUserInformationService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    private static final Logger logger = Logger.getLogger(BasicUserInformationService.class);

    @Autowired
    public BasicUserInformationService(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto updateUserBasicInformation(final UserUpdateDto userUpdate) throws NoSuchUserException {
        User user = userMapper.mapUserUpdateDtoToUser(userUpdate);

        User user1 = userRepository.add(user);

        return userMapper.mapEntityToDto(user1);
    }

    public UserDto findUserById(Long user_id) throws NoSuchUserException {
        User user_Id = userRepository.findById(user_id);

        return userMapper.mapEntityToDto(user_Id);
    }

    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();

        return userMapper.mapListToDto(users);
    }

    public void removeUser(UserDto userDto) throws NoSuchUserException {
        User user = userMapper.mapDtoToEntity(userDto);
        userRepository.remove(user);
    }

    public StatisticsDto getUserStatistics(Long userId) throws NoSuchUserException { // liczba wygranych, przegranych zremisowanych
        StatisticsDto statisticsDto= userRepository.findById(userId);

        return null;
    }

    private RankingPositionDto getPositionInRanking(Long id) {

        return null;
    }

    private long numberOfWonGames(Long userId) throws NoSuchUserException {
        return userRepository.findById(userId).getUserPreviousGames().stream()
                .filter(previousGames -> previousGames.getGameResult().equals(GameResult.WIN))
                .count();
    }

    private long numberOfDraw(Long userId) throws NoSuchUserException {
        return userRepository.findById(userId).getUserPreviousGames().stream()
                .filter(previousGames -> previousGames.getGameResult().equals(GameResult.DRAW))
                .count();
    }

    private long numberOfLoseGames(Long userId) throws NoSuchUserException {
        return userRepository.findById(userId).getUserPreviousGames().stream()
                .filter(previousGames -> previousGames.getGameResult().equals(GameResult.LOSE))
                .count();
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
        }catch(NoSuchUserException e){
            logger.error("There is no user with such name");
        }
        return null;
    }

}
