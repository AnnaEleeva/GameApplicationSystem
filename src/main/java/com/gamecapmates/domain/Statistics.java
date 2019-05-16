package com.gamecapmates.domain;

import com.gamecapmates.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Statistics {
    private Long userId;
    private Level level;
    private long gameWin;
    private long gameLose;
    private long gameDraw;
    private Long rankingPosition;
}
