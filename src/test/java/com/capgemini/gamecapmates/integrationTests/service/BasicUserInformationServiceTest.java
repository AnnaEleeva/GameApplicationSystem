package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class BasicUserInformationServiceTest {

    @Autowired
    private BasicUserInformationService basicUserInformationService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void shouldUpdateUserBasicInformation() throws NoSuchUserException {
        //given
        final UserUpdateDto userUpdate = new UserUpdateDto(2L, "Adam", "Janusz", "janusz1@gmail.com", "janusz", LocalDate.of(1995, 1, 1), "Janusz");
        final UserDto expecteduser = userMapper.mapUserUpdateToDto(userUpdate);
        //when
        UserDto result = basicUserInformationService.updateUserBasicInformation(userUpdate);
        //then
        assertEquals(expecteduser, result);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowExceptionWhenUpdateUser() throws NoSuchUserException {
        //given
        final UserUpdateDto userUpdate = new UserUpdateDto(8L, "Adam", "Janusz", "janusz1@gmail.com", "janusz", LocalDate.of(1995, 1, 1), "Janusz");
        final UserDto expecteduser = userMapper.mapUserUpdateToDto(userUpdate);
        //when
        UserDto result = basicUserInformationService.updateUserBasicInformation( userUpdate);
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



}
