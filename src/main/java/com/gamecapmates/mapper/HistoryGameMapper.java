package com.gamecapmates.mapper;

import com.gamecapmates.domain.GamesHistory;
import com.gamecapmates.dto.GamesHistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryGameMapper {

    public List<GamesHistoryDto> mapHistoryListToHistoryDto(List<GamesHistory> historyList) {
        return historyList.stream()
                .map(gamesHistory -> new GamesHistoryDto(
                        gamesHistory.getId(),
                        gamesHistory.getIdGame(),
                        gamesHistory.getDate(),
                        gamesHistory.getAction(),
                        gamesHistory.getPlayers(),
                        gamesHistory.getGameResult(),
                        gamesHistory.getRating()
                )).collect(Collectors.toList());

    }
}