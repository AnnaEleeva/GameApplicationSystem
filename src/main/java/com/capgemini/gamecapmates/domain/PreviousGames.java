package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PreviousGames {
    private Long idGame;
    private List<Long> idPlayers;
    private Boolean isWinner;
}
