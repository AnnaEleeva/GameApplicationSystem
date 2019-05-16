package com.gamecapmates.integrationTests.service;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.GameCapMatesBoardApplication;
import com.gamecapmates.dto.StatisticsDto;
import com.gamecapmates.dto.UserDto;
import com.gamecapmates.dto.UserUpdateDto;
import com.gamecapmates.mapper.UserMapper;
import com.gamecapmates.service.BasicUserInformationService;
import com.gamecapmates.enums.Level;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class BasicUserInformationServiceTest {

    @Autowired
    private BasicUserInformationService basicUserInformationService;
    @Autowired
    private UserMapper userMapper;


    @Test
    public void shouldUpdateUserBasicInformation() throws NoSuchUserException {
        //given
        final UserUpdateDto userUpdate = new UserUpdateDto(2L, "Adam", "Janusz", "janusz1@gmail.com", "janusz", LocalDate.of(1995, 1, 1), "Janusz");
        final UserDto expectedUser = userMapper.mapUserUpdateToDto(userUpdate);
        //when
        UserDto result = basicUserInformationService.updateUserBasicInformation(userUpdate);
        UserDto resultUser = basicUserInformationService.findUserById(2L);
        //then
        assertEquals(expectedUser, result);
        assertEquals(expectedUser, resultUser);
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
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        //when
        UserDto result = basicUserInformationService.findUserById(1L);
        //then
        assertEquals(expectedUser, result);
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
        basicUserInformationService.removeUser(expected);
    }

    @Test(expected = NoSuchUserException.class)
    public void ShouldReturnExceptionWhenAddNull() throws NoSuchUserException {
        //given
        basicUserInformationService.addUserToRepository(null);
        //when
        //then
    }

    @Test(expected = NoSuchUserException.class)
    public void findUserByIdNotInRepositoryShouldReturnExceptionTest() throws NoSuchUserException {
        //given
        basicUserInformationService.findUserById(9L);
        //when
        //then
    }

    @Test
    public void findAllUserFromRepositoryWithAddUser() throws NoSuchUserException {
        //given
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
        List<UserDto> allUserProfiles = basicUserInformationService.findAllUser();
        int size = allUserProfiles.size();
        //then
        assertEquals(4, size);
        basicUserInformationService.removeUser(expected);

    }

    @Test
    public void findAllAfterRemoveUserFromRepository() throws NoSuchUserException {
        //given
        UserDto expectedUser = UserDto.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        //when
        basicUserInformationService.removeUser(expectedUser);
        List<UserDto> allUserProfiles = basicUserInformationService.findAllUser();
        int size = allUserProfiles.size();
        //then
        assertEquals(2, size);
    }

    @Test(expected = NoSuchUserException.class)
    public void removeNullUserWithException() throws NoSuchUserException {
        //given
        //when
        basicUserInformationService.removeUser(null);
        //then
    }

    @Test(expected = NoSuchUserException.class)
    public void removeUserNotInRepositoryWithException() throws NoSuchUserException {
        //given
        UserDto expected = UserDto.builder()
                .id(7L)
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
        //when
        basicUserInformationService.removeUser(expected);
        //then
    }

    @Test(expected = NoSuchUserException.class)
    public void getUserStatisticsFromUserOutOfBoundsTest() throws NoSuchUserException {
        //given
        //when
        StatisticsDto userStat = basicUserInformationService.getUserStatistics(6L);
        //then
    }

    @Test
    public void getUserStatisticsTestWhenAddUser() throws NoSuchUserException {
        //given
        UserDto add = UserDto.builder()
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
        basicUserInformationService.addUserToRepository(add);

        StatisticsDto expected = StatisticsDto.builder()
                .userId(4L)
                .level(Level.CAN_I_PLAY_DADDY)
                .gameWin(1)
                .gameLose(1)
                .gameDraw(0)
                .rankingPosition(1L) //ada method
                .build();

        //when
        StatisticsDto userStat = basicUserInformationService.getUserStatistics(4L);
        //then
        assertEquals(expected, userStat);
    }
}
