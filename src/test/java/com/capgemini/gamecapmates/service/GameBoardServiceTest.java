package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.AppConfig;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.repository.GameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
@Import(AppConfig.class)
public class GameBoardServiceTest {

    @Autowired
    BoardGamesService boardGamesService;
    @Autowired
    GameRepository gameRepository;

    @Test
    public void addGameAlreadyExistsToUserTest(){
        boardGamesService.addGameAlreadyExistsToUser(1L,2L);

        int size=boardGamesService.getUserGames(1L).size();

        Assert.assertEquals(1,size);
    }

    @Test
    public void addGameThatIsNotExistsToUserTest(){
    //    boardGamesService.addGameThatIsNotExistsToUser(5L,new GameDto(5L,"Wolfenstein: New Castle", LocalDate.of(2018,11,14),6,5,""));

        int size=boardGamesService.getUserGames(1L).size();

        System.out.println(size);
    }
}
