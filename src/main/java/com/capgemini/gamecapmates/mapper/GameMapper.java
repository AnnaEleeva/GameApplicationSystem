package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.validation.GamesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    @Autowired
    private GamesValidator gamesValidator;

    public Game mapDtoToEntity (GameDto gameDto)  {

        return Game.builder()
                .id(gameDto.getId())
                .name(gameDto.getName())
                .year_of_publishment(gameDto.getYear_of_publishment())
                .minNumberOfPlayers(gameDto.getMinNumberOfPlayers())
                .maxNumberOfplayers(gameDto.getMaxNumberOfplayers())
                .category(gameDto.getCategory())
                .description(gameDto.getDescription())
                .build();
    }

    public GameDto mapEntityToDto(Game game) {

        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .year_of_publishment(game.getYear_of_publishment())
                .minNumberOfPlayers(game.getMinNumberOfPlayers())
                .maxNumberOfplayers(game.getMaxNumberOfplayers())
                .category(game.getCategory())
                .description(game.getDescription())
                .build();
    }


    public List<GameDto> mapListToDto(List<Game> games) {
        return games.stream()
                .map(game -> new GameDto(
                        game.getId(),
                        game.getName(),
                        game.getYear_of_publishment(),
                        game.getMinNumberOfPlayers(),
                        game.getMaxNumberOfplayers(),
                        game.getCategory(),
                        game.getDescription()
                )).collect(Collectors.toList());
    }
}
