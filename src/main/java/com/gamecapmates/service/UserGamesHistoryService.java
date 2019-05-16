package com.gamecapmates.service;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.domain.GamesHistory;
import com.gamecapmates.dto.GamesHistoryDto;
import com.gamecapmates.mapper.HistoryGameMapper;
import com.gamecapmates.repository.GamesHistoryRepository;
import com.gamecapmates.repository.UserRepository;
import com.gamecapmates.validation.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGamesHistoryService {

    private GamesHistoryRepository historyRepository;
    private HistoryGameMapper historyGameMapper;
    private UserRepository userRepository;
    private UserValidator userValidator;

    public UserGamesHistoryService(GamesHistoryRepository gamesHistoryRepository, HistoryGameMapper historyGameMapper, UserRepository userRepository, UserValidator userValidator) {
        this.historyRepository = gamesHistoryRepository;
        this.historyGameMapper = historyGameMapper;
        this.userRepository = userRepository;
        this.userValidator= userValidator;
    }
    /**
     * Finding history of played games of specific user
     * @param userId find user by Id
     * @throws NoSuchUserException check if user is in system collection
     * */
    public List<GamesHistoryDto> findAllUserGameHistory(Long userId) throws NoSuchUserException {
        userValidator.checkIfUserIdIsNull(userId);

        if(userRepository.findAll().contains(userRepository.findById(userId))) {

            List<Long> userGamesId = userRepository.findById(userId).getUserGamesHistory();
            List<GamesHistory> historyList = historyRepository.findAll();
            List<GamesHistory> userHistoryList = historyList.stream().filter(gamesHistory -> userGamesId.contains(gamesHistory.getId()))
                    .collect(Collectors.toList());

            return historyGameMapper.mapHistoryListToHistoryDto(userHistoryList);
        }
        throw new NoSuchUserException();
    }
}
