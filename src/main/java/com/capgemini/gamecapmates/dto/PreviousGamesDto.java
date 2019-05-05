package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.GameResult;
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
    private Long idUser;
    private Long idGame;
    private GameResult gameResult;
}
