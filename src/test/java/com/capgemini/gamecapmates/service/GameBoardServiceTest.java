package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class GameBoardServiceTest {

    @Autowired
    BoardGamesService boardGamesService;

    @Test
    public void addGameAlreadyExistsToUserTest(){

    }

    @Test
    public void addGameThatIsNotExistsToUserTest(){

    }
}
