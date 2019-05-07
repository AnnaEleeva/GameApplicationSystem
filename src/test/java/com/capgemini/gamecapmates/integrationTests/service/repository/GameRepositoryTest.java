package com.capgemini.gamecapmates.integrationTests.service.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.enums.Category;
import com.capgemini.gamecapmates.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class GameRepositoryTest {

    private GameRepository gameRepository;

    @Before
    public void setUp(){
        gameRepository= new GameRepository();
    }

    @Test
    public void shouldReturnEntityWhenGetById() throws NoSuchGameException {
        //given
        Game game= Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        //when
        Game result = gameRepository.findById(1L);
        //then
        assertEquals(game, result);
    }

    @Test(expected = NoSuchGameException.class)
    public void shouldThrowExceptionWhenIdNotExistOnList() throws NoSuchGameException {
        //given
        //when
        gameRepository.findById(99L);
    }

    @Test
    public void shouldReturnAvailabilityListWhenGetAll() {
        //given
        List<Game> expectedList = generateList();
        //when
        List<Game> result = gameRepository.findAll();
        //then
        assertEquals(expectedList, result);
    }

    @Test
    public void shouldReturnAddedElementAndAddToList() throws NoSuchGameException {
        //given
        Game game= Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        game.setId(5L);
        //when
         gameRepository.add(game);
        //then
        assertEquals(game, gameRepository.findById(5L));
    }

    @Test
    public void shouldReturnUpdatedElementWhenUpdate() throws NoSuchGameException, NoSuchUserException {
        //given
        String expectedname = "cards";

        Game game= Game.builder()
                .id(1L)
                .name(expectedname)
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();

        //when
        Game result = gameRepository.edit(game);
        String resultName = gameRepository.findById(1L).getName();
        //then
        assertEquals(game, result);
        assertEquals(expectedname, resultName);
    }

    @Test
    public void shouldReturnDeletedElement() {
        //given
        Game game= Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        //when
         gameRepository.remove(game);
        //then
        assertEquals(Long.valueOf(1L), gameRepository.findAll().get(0).getId());
    }

    public List<Game> generateList(){
        List<Game> gameList= new ArrayList<>();

        gameList.add(Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Wolfenstein: New Castle")
                .year_of_publishment(LocalDate.of(2018, 1, 1))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(5)
                .category(Category.STRATEGY)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Lich King")
                .year_of_publishment(LocalDate.of(2018, 7, 17))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(6)
                .category(Category.CARD)
                .description("Searching for players with skill")
                .build());

        return gameList;
    }
}
