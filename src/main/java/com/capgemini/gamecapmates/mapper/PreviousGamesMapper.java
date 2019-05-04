package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.GamesHistory;
import com.capgemini.gamecapmates.dto.GamesHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class PreviousGamesMapper {

    public GamesHistoryDto mapToHistoryDto(GamesHistory gamesHistory){
        return GamesHistoryDto.builder()
                .date(gamesHistory.getDate())
                .action(gamesHistory.getAction())
                .details(gamesHistory.getDetails())
                .rating(gamesHistory.getRating())
                .build();
    }
}
