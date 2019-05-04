package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public Game mapDtoToEntity (GameDto gameDto){
        return Game.builder()
                .id(gameDto.getId())
                .name(gameDto.getName())
                .year_of_publishment(gameDto.getYear_of_publishment())
                .minNumberOfPlayers(gameDto.getMinNumberOfPlayers())
                .maxNumberOfplayers(gameDto.getMaxNumberOfplayers())
                .description(gameDto.getDescription())
                .build();
    }

    public GameDto mapEntityToDto(Game game){
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .year_of_publishment(game.getYear_of_publishment())
                .minNumberOfPlayers(game.getMinNumberOfPlayers())
                .maxNumberOfplayers(game.getMaxNumberOfplayers())
                .description(game.getDescription())
                .build();
    }
}
