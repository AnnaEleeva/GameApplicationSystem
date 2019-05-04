package com.capgemini.gamecapmates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PreviousGamesDto {
    private Long idGame;
    private List<Long> idPlayers;
    private Boolean isWinner;
}
