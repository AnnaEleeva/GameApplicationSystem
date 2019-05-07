package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class BasicUserInformationServiceTest {

    @Autowired
    private BasicUserInformationService basicUserInformationService;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() throws NoSuchUserException {
    }

    @Test
    public void shouldUpdateUserBasicInformation() throws NoSuchUserException {
        //given
        final UserUpdateDto userUpdate = new UserUpdateDto(2L, "Adam", "Janusz", "janusz1@gmail.com", "janusz", LocalDate.of(1995, 1, 1), "Janusz");
        final UserDto expecteduser = userMapper.mapUserUpdateToDto(userUpdate);
        //when
        UserDto result = basicUserInformationService.updateUserBasicInformation(userUpdate);
        UserDto resultUser= basicUserInformationService.findUserById(2L);
        //then
        assertEquals(expecteduser, result);
        assertEquals(expecteduser,resultUser);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowExceptionWhenUpdateUser() throws NoSuchUserException {
        //given
        final UserUpdateDto userUpdate = new UserUpdateDto(8L, "Adam", "Janusz", "janusz1@gmail.com", "janusz", LocalDate.of(1995, 1, 1), "Janusz");
        final UserDto expecteduser = userMapper.mapUserUpdateToDto(userUpdate);
        //when
        UserDto result = basicUserInformationService.updateUserBasicInformation(userUpdate);
        //then
        assertEquals(expecteduser, result);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowExceptionWhenUpdateUserNull() throws NoSuchUserException {
        //given
        //when
        basicUserInformationService.updateUserBasicInformation(null);
        //then
    }

    @Test
    public void findUserByIdTestWithUserRepositoryDb() throws NoSuchUserException {
        //given
        UserDto expectedUser = UserDto.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L, 4L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        //when
        UserDto result=basicUserInformationService.findUserById(1L);
        //then
        assertEquals(expectedUser,result);
    }

    @Test
    public void findUserByIdTestWithAddingUserToRepository() throws NoSuchUserException {
        // given
        UserDto expected = UserDto.builder()
                .id(4L)
                .age(28)
                .firstName("Bogdan")
                .lastName("Kowalski")
                .email("Kowalski1@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L, 4L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        basicUserInformationService.addUserToRepository(expected);
        //when
        UserDto result = basicUserInformationService.findUserById(4L);
        // then
        assertEquals(expected, result);
    }

    @Test(expected = NoSuchUserException.class)
    public void findUserByIdTestShouldReturnExceptionWhenAddNull() throws NoSuchUserException {
        //given
        basicUserInformationService.addUserToRepository(null);
        //when
        //then
    }



}
