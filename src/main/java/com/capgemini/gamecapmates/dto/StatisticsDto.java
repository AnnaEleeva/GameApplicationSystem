package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class StatisticsDto {
    private Long userId;
    private Level level;
    private long gameWin;
    private long gameLose;
    private long gameDraw;
    private Long rankingPosition;
}
