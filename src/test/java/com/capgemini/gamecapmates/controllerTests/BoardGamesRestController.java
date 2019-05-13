package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.restService.BoardGamesWebService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardGamesRestController {

    private MockMvc mockMvc;

    @Mock
    private BoardGamesService boardGamesService;

    @Mock
    private BoardGamesWebService boardGamesWebService;

    @InjectMocks
    private BoardGamesRestController boardGamesRestController;

    private List<GameDto> gameDtos;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(boardGamesRestController).build();
       gameDtos= generateList();
    }

    @Test
    public void shouldReturnGameById(){
        //given
        //when
        //then
    }

    @Test
    public void shouldNotReturnGameById(){
        //given
        //when
        //then
    }

    @Test
    public void shouldFindAllGame(){
        //given
        //when
        //then
    }

    @Test
    public void shouldSearchGame(){
        //given
        //when
        //then
    }

    @Test
    public void shouldNotSearchGam(){
        //given
        //when
        //then
    }

    private List<GameDto> generateList(){
        return null;
    }
}
