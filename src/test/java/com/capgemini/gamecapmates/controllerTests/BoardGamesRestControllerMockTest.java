package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.controller.BoardGamesRestController;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.enums.Category;
import com.capgemini.gamecapmates.service.BoardGamesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardGamesRestControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private BoardGamesService boardGamesService;


    @InjectMocks
    private BoardGamesRestController boardGamesRestController;


    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(boardGamesRestController).build();
    }

    @Test
    public void shouldReturnGameById()throws Exception{
        //given
        GameDto boardGameDto=GameDto.builder()
                .id(3L)
                .name("Lordearon")
                .year_of_publishment(LocalDate.of(1998, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build();
        //when
        when(boardGamesService.getGameById(any(Long.class))).thenReturn(boardGameDto);
        ResultActions resultActions= mockMvc.perform(get("/api/games/game/3"));
        //then
        Mockito.verify(boardGamesService,times(1)).getGameById(3L);
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(boardGameDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(boardGameDto.getName())));
    }

    @Test
    public void shouldAddGameToCollection() throws Exception {
        //given
        GameDto gameDto = new GameDto();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gameDto);
        //when
        when(boardGamesService.addGame(gameDto)).thenReturn(gameDto);
        mockMvc.perform(post("/api/games/addGame").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
