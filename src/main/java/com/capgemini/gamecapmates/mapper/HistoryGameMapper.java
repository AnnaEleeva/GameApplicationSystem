package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.GamesHistory;
import com.capgemini.gamecapmates.dto.GamesHistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryGameMapper {

    public GamesHistoryDto mapToHistoryDto(GamesHistory gamesHistory) {
        return GamesHistoryDto.builder()
                .id(gamesHistory.getId())
                .idGame(gamesHistory.getIdGame())
                .date(gamesHistory.getDate())
                .action(gamesHistory.getAction())
                .players(gamesHistory.getPlayers())
                .gameResult(gamesHistory.getGameResult())
                .rating(gamesHistory.getRating())
                .build();
    }

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