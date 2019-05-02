package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GameBoardDto {
    private Long id;
    private Optional<Game> gameBoard;
    private Optional<User> userBoard;
}
