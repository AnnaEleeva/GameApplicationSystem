package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.GamesHistory;
import com.capgemini.gamecapmates.dto.GamesHistoryDto;
import com.capgemini.gamecapmates.mapper.HistoryGameMapper;
import com.capgemini.gamecapmates.repository.GamesHistoryRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGamesHistoryService {

    private GamesHistoryRepository historyRepository;
    private HistoryGameMapper historyGameMapper;
    private UserRepository userRepository;

    public UserGamesHistoryService(GamesHistoryRepository gamesHistoryRepository, HistoryGameMapper historyGameMapper, UserRepository userRepository) {
        this.historyRepository = gamesHistoryRepository;
        this.historyGameMapper = historyGameMapper;
        this.userRepository = userRepository;
    }

    public List<GamesHistoryDto> findAllUserGameHistory(Long userId) throws NoSuchUserException {
        List<Long> userGamesId = userRepository.findById(userId).getUserGamesHistory();
        List<GamesHistory> historyList = historyRepository.findAll();
        List<GamesHistory> userHistoryList = historyList.stream().filter(gamesHistory -> userGamesId.equals(gamesHistory.getId()))
                .collect(Collectors.toList());

        return historyGameMapper.mapHistoryListToHistoryDto(userHistoryList);
    }
}
