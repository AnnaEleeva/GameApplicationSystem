package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.enums.GameResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GamesHistoryDto {
    private Long id;
    private Long idGame;
    private LocalDate date;
    private String action;
    private ArrayList<Long> players;
    private GameResult gameResult;
    private Double rating;
}
