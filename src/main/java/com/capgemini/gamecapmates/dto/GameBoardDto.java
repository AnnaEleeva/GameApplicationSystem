package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class GameBoardDto {
    private Map<User, Game> gameBoard;
}
