package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;

public class GameMapper {

    public Game mapDtoToEntity (GameDto gameDto){
        return Game.builder()
                .id(gameDto.getId())
                .name(gameDto.getName())
                .year_of_publishment(gameDto.getYear_of_publishment())
                .number_of_players1(gameDto.getNumber_of_players1())
                .number_of_players2(gameDto.getNumber_of_players2())
                .description(gameDto.getDescription())
                .build();
    }

    public GameDto mapEntityToDto(Game game){
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .year_of_publishment(game.getYear_of_publishment())
                .number_of_players1(game.getNumber_of_players1())
                .number_of_players2(game.getNumber_of_players2())
                .description(game.getDescription())
                .build();
    }
}
