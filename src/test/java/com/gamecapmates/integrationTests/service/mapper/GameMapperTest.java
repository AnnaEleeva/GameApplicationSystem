package com.gamecapmates.integrationTests.service.mapper;

import com.gamecapmates.Exceptions.NoSuchGameException;
import com.gamecapmates.GameCapMatesBoardApplication;
import com.gamecapmates.domain.Game;
import com.gamecapmates.dto.GameDto;
import com.gamecapmates.enums.Category;
import com.gamecapmates.mapper.GameMapper;
import com.gamecapmates.repository.GameRepository;
import com.gamecapmates.service.BoardGamesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class GameMapperTest {

    @Autowired
    GameMapper gameMapper;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    BoardGamesService boardGamesService;

    @Test
    public void mapDtoToEntityTest() throws NoSuchGameException {
        //given
        Game game = Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        //when
        GameDto game1= boardGamesService.getGameById(1L);
        Game game2= gameMapper.mapDtoToEntity(game1);
        //then
        assertEquals(game,game2);
    }

    @Test
    public void mapEntityToDto() throws NoSuchGameException {
        //Given
        GameDto game = GameDto.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        //When
        Game game1= gameRepository.findById(1L);
        GameDto gamDto=gameMapper.mapEntityToDto(game1);
        //Then
        assertEquals(game,gamDto);
    }

    @Test
    public void mapListToDto(){
        //Given
       List<Game> gameList = new ArrayList<>();

        gameList.add(Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Wolfenstein: New Castle")
                .year_of_publishment(LocalDate.of(2018, 1, 1))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(5)
                .category(Category.STRATEGY)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Lich King")
                .year_of_publishment(LocalDate.of(2018, 7, 17))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(6)
                .category(Category.CARD)
                .description("Searching for players with skill")
                .build());
        //When
        List<GameDto> games = boardGamesService.findAllGame();
        List<GameDto> games1 = gameMapper.mapListToDto(gameList);
        //Then
        assertEquals(games, games1);
    }
}
