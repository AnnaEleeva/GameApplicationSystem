package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.service.UserGamesHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class UserGamesHistoryServiceTest {

    @Autowired
    private UserGamesHistoryService userGamesHistoryService;

    @Test
    public void shouldfindAllUserGameHistoryFromUser(){
        //given

        //when
        //then
    }

    @Test
    public void shouldfindAllUserGameHistoryFromUserThatIsNotExist(){
        //given
        //when
        //then
    }

    @Test
    public void shouldfindAllUserGameHistoryFromUserThatIsNull(){
        //given
        //when
        //then
    }
}
