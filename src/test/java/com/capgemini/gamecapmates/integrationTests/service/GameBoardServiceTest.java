package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.enums.Category;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class GameBoardServiceTest {

    @Autowired
    BoardGamesService boardGamesService;

    @Test
    public void findAllGameFromRepository(){
        //given

        //when
        List<GameDto> gamesServiceAllGame =boardGamesService.findAllGame();
        int size = gamesServiceAllGame.size();
        //then
        assertEquals(3,size);
    }

    @Test
    public void findAllGameFromRepositoryAfterAddGame() throws NoSuchGameException, NoSuchUserException {
        //given
        GameDto addGame = GameDto.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();

        boardGamesService.addGameThatIsNotExistsToCollection(4L,addGame);
        //when
        List<GameDto> gamesServiceAllGame = boardGamesService.findAllGame();
        int size = gamesServiceAllGame.size();
        //then
        assertEquals(5, size);
    }

    @Test
    public void addGameAlreadyExistsToUserTest(){

    }

    @Test
    public void addGameThatIsNotExistsToUserTest(){

    }
}
