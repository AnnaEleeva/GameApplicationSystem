package com.gamecapmates.integrationTests.service.mapper;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.GameCapMatesBoardApplication;
import com.gamecapmates.domain.User;
import com.gamecapmates.dto.UserDto;
import com.gamecapmates.dto.UserUpdateDto;
import com.gamecapmates.mapper.UserMapper;
import com.gamecapmates.repository.UserRepository;
import com.gamecapmates.service.BasicUserInformationService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class UserMapperTest {

    @Autowired
    BasicUserInformationService basicUserInformationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    public void shouldMapDtoToEntityTest() throws NoSuchUserException {
        //given
        User user=User.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L,2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        //when
        UserDto userDto= basicUserInformationService.findUserById(1L);
        User user1= userMapper.mapDtoToEntity(userDto);
        //then
        assertEquals(user,user1);
    }

    @Test
    public void shouldMapEntityToDtoTest() throws NoSuchUserException {
        //given
        UserDto user = UserDto.builder()
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
        User user1 = userRepository.findById(1L);
        UserDto user2 = userMapper.mapEntityToDto(user1);
        //then
        assertEquals(user, user2);
    }

    @Test
    public void shouldMapUserUpdateToDtoTest() throws NoSuchUserException {
        //given
        User user=User.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .build();
        UserUpdateDto userUpdateDto= UserUpdateDto.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .birthDate(LocalDate.of(1991,1,1))
                .motto("This is sparta")
                .build();
        //when
        User user1= userMapper.mapUserUpdateDtoToUser(userUpdateDto);
        //then
        assertEquals(user,user1);
    }

    @Test
    public void shouldMapListToDto(){
        //given
        List<User> userList = new ArrayList<>();

        userList.add(User.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L,2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build());
        userList.add(User.builder()
                .id(2L)
                .age(25)
                .firstName("Marcin")
                .lastName("Nowak")
                .email("Marcin1@gmail.com")
                .password("Nowak1")
                .motto("To the death!")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(1L)))
                .build());
        userList.add(User.builder()
                .id(3L)
                .age(25)
                .firstName("Adam")
                .lastName("Bak")
                .email("Adas2@onet.pl")
                .password("Bak1")
                .motto("Dont die")
                .userGames(new ArrayList<>(Arrays.asList(1L,2L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(3L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(3L)))
                .build());
        //when
        List<UserDto> users = basicUserInformationService.findAllUser();
        List<UserDto> users1 = userMapper.mapListToDto(userList);
        //then
        assertEquals(users1,users);
    }
}
