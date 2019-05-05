package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GameBoardDto {
    private Game gameBoard;
    private User userBoard;

}
