package com.gamecapmates.integrationTests.service.repository;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.GameCapMatesBoardApplication;
import com.gamecapmates.domain.User;
import com.gamecapmates.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp(){
        userRepository= new UserRepository();
    }

    @Test
    public void shouldReturnEntityWhenGetById() throws NoSuchUserException {
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
        User result = userRepository.findById(1L);
        //then
        assertEquals(user, result);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowExceptionWhenIdNotExistOnList() throws NoSuchUserException {
        //given
        //when
        userRepository.findById(9L);
    }

    @Test
    public void shouldReturnAvailabilityListWhenGetAll() {
        //given
        List<User> expectedList = generateList();
        //when
        List<User> result = userRepository.findAll();

        //then
        assertEquals(3, result.size());
    }

    @Test
    public void shouldReturnAddedElementAndAddToList() throws NoSuchUserException {
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
        user.setId(5L);
        //when
        User result = userRepository.add(user);
        //then
        assertEquals(user, result);
        assertEquals(user, userRepository.findById(5L));
    }

    @Test
    public void shouldReturnUpdatedElementWhenUpdate() throws NoSuchUserException {
        //given
        String expectedLastName = "Kulczyk";

        User userExpected=User.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName(expectedLastName)
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L,2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build();
        //when
        User result = userRepository.edit(userExpected);
        String resultLastName = userRepository.findById(1L).getLastName();
        //then
        assertEquals(userExpected, result);
        assertEquals(expectedLastName, resultLastName);
    }

    @Test
    public void shouldReturnDeletedElement() {
        //given
        User user=User.builder()
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
         userRepository.remove(user);
        //then
        assertEquals(Long.valueOf(2L), userRepository.findAll().get(0).getId());
    }

    private List<User> generateList() {
        List<User> userList = new ArrayList<>();

        userList.add(User.builder()
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
                .build());
        userList.add(User.builder()
                .id(2L)
                .age(25)
                .firstName("Marcin")
                .lastName("Nowak")
                .email("Marcin1@gmail.com")
                .password("Nowak1")
                .motto("To the death!")
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
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
                .userGames(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(3L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(3L)))
                .build());

        return userList;
    }
}
