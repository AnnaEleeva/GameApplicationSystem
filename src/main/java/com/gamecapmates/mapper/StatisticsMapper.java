package com.gamecapmates.mapper;

import com.gamecapmates.domain.Statistics;
import com.gamecapmates.dto.StatisticsDto;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {

    public Statistics mapDtotoEntity(StatisticsDto statisticsDto){
        return Statistics.builder()
             .userId(statisticsDto.getUserId())
                .level(statisticsDto.getLevel())
                .gameWin(statisticsDto.getGameWin())
                .gameLose(statisticsDto.getGameLose())
                .gameDraw(statisticsDto.getGameDraw())
                .rankingPosition(statisticsDto.getRankingPosition())
                .build();
    }
}

