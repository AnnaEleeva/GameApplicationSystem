package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GameBoard {
    private Game gameBoard;
    private User userBoard;
}
