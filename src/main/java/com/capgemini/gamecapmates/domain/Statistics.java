package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
class Statistics {
    private Level level;
    private long gameWin;
    private long gameLose;
    private long gameDraw;
}
