package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.enums.Category;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class GameBoardServiceTest {

    @Autowired
    BoardGamesService boardGamesService;

    @Test
    public void findAllGameFromRepositoryAfterAddNotExistGameToUser() throws NoSuchGameException, NoSuchUserException {
        //given
        GameDto addGame = GameDto.builder()
                .id(1L)
                .name("Devil from Hell")
                .year_of_publishment(LocalDate.of(2017, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.CARD)
                .description("Searching for players with skill")
                .build();

        boardGamesService.addGameThatIsNotExistsToCollection(2L,addGame);
        //when
        List<GameDto> gamesServiceAllGame = boardGamesService.findAllGame();
        int size = gamesServiceAllGame.size();
        List<GameDto> userGames= boardGamesService.getUserGamesCollection(2L);
        int sizeOfUserGames= userGames.size();
        //then
        assertEquals(4,sizeOfUserGames);
        assertEquals(4, size);
    }


    @Test
    public void FindAllGamesFromUserAfterAdd2GamesAlreadyExistsToUserTest() throws NoSuchUserException, NoSuchGameException {
        //given
        boardGamesService.addGameAlreadyExistsToUser(1L, 1L);
        //when
        List<GameDto> userGamesCollection= boardGamesService.getUserGamesCollection(1L);
        int size = userGamesCollection.size();
        //then
        System.out.println(userGamesCollection);
        assertEquals(4,size);
    }

    @Test
    public void shouldReturnGameAfterAddGameFromGetGameById() throws NoSuchGameException, NoSuchUserException {
        //given
        GameDto addGame = GameDto.builder()
                .id(5L)
                .name("Devil from Hell")
                .year_of_publishment(LocalDate.of(2017, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.CARD)
                .description("Searching for players with skill")
                .build();

        boardGamesService.addGameThatIsNotExistsToCollection(2L,addGame);
        //when
        GameDto game=boardGamesService.getGameById(5L);
        //then
        assertEquals(addGame,game);
    }

    @Test(expected = NoSuchGameException.class )
    public void ShouldReturnExceptionAfterGetGameByIdThatIsNotExist() throws NoSuchGameException {
        //given
        //when
        boardGamesService.getGameById(7L);
        //then
    }

    @Test(expected = NoSuchGameException.class)
    public void ShouldReturnExceptionAfterGetGameByNullId() throws NoSuchGameException {
        //given
        //when
        boardGamesService.getGameById(null);
        //then
    }

    @Test (expected = NoSuchUserException.class)
    public void getUserGamesCollectionThatDoesNotExistTest() throws NoSuchUserException {
        //given
        //when
        boardGamesService.getUserGamesCollection(7L);
        //then
    }


    @Test (expected = NoSuchUserException.class)
    public void getUserGamesCollectionThatIsNullTest() throws NoSuchUserException {
        //given
        //when
        boardGamesService.getUserGamesCollection(null);
        //then
    }


    @Test(expected = NoSuchUserException.class)
    public void removeGameFromUserCollectionThatIsNotExist() throws NoSuchUserException, NoSuchGameException {
        //given
        //when
        boardGamesService.removeGameFromUserCollection(5L,1L);
        //then
    }

    @Test (expected = NoSuchGameException.class)
    public void removeGameThatIsNotExistFromUserCollection() throws NoSuchUserException, NoSuchGameException {
        //given
        //when
        boardGamesService.removeGameFromUserCollection(1L,6L);
        //then
    }

}
