package com.capgemini.gamecapmates.domain;

import com.capgemini.gamecapmates.enums.GameResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GamesHistory {
    private Long id;
    private Long idGame;
    private LocalDate date;
    private String action;
    private ArrayList<Long> players;
    private GameResult gameResult;
    private Double rating;
}
