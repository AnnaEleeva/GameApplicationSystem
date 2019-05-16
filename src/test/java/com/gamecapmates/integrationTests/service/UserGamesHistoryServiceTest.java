package com.gamecapmates.integrationTests.service;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.GameCapMatesBoardApplication;
import com.gamecapmates.dto.GamesHistoryDto;
import com.gamecapmates.dto.UserDto;
import com.gamecapmates.repository.UserRepository;
import com.gamecapmates.service.BasicUserInformationService;
import com.gamecapmates.service.UserGamesHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class UserGamesHistoryServiceTest {

    @Autowired
    private UserGamesHistoryService userGamesHistoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasicUserInformationService basicUserInformationService;

    @Test
    public void shouldfindAllUserGameHistoryFromUser() throws NoSuchUserException {
        //given
        //when
       List<GamesHistoryDto> userGamesHistory= userGamesHistoryService.findAllUserGameHistory(1L);
        //then
        assertEquals(2,userGamesHistory.size());
    }

    @Test
    public void shouldfindAllUserGameHistoryFromUserAfterAddUser() throws NoSuchUserException {
        //given
        UserDto expectedUser = UserDto.builder()
                .id(4L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L,3L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();

        basicUserInformationService.addUserToRepository(expectedUser);
        //when
        List<GamesHistoryDto> userGamesHistory= userGamesHistoryService.findAllUserGameHistory(4L);
        //then
        assertEquals(3,userGamesHistory.size());
    }

    @Test (expected = NoSuchUserException.class)
    public void shouldNotFindAllUserGameHistoryFromUserThatIsNotExist() throws NoSuchUserException {
        //given
        //when
        userGamesHistoryService.findAllUserGameHistory(5L);
        //then
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldfindAllUserGameHistoryFromUserThatIsNull() throws NoSuchUserException {
        //given
        //when
        userGamesHistoryService.findAllUserGameHistory(null);
        //then
    }

}
