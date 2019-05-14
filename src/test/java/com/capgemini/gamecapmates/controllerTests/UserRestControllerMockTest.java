package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.controller.UserRestController;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRestControllerMockTest {

    @Mock
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    @Mock
    private BasicUserInformationService basicUserInformationService;

    @InjectMocks
    private UserRestController userRestController;

    private List<UserDto> listuserDtos;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(userRestController).build();
        listuserDtos = generateList();
    }

    @Test
    public void shouldGetUserFromCollection() throws Exception{
        //given
        //when
        //given
        //when
        when(basicUserInformationService.findUserById(any(Long.class))).thenReturn(listuserDtos.get(0));
        ResultActions resultActions= mockMvc.perform(get("/api/users/user/3"));
        //then
        Mockito.verify(basicUserInformationService,times(1)).findUserById(any(Long.class));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(listuserDtos.get(0).getFirstName())));

    }
    @Test
    public void shouldAddUserToCollection() throws Exception {
        //given
        UserDto userDto = new UserDto();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userDto);
        //when
        when(basicUserInformationService.addUser(any(UserDto.class))).thenReturn(userDto);
        ResultActions resultActions = mockMvc.perform(post("/api/users/addUser")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
    }

    private List<UserDto> generateList(){
        List<UserDto> userList = new ArrayList<>();

        userList.add(UserDto.builder()
                .id(3L)
                .age(28)
                .firstName("Adam")
                .lastName("Konieczny")
                .email("konik@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L, 2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build());
        userList.add(UserDto.builder()
                .id(4L)
                .age(25)
                .firstName("Bogumil")
                .lastName("Nowak")
                .email("bogumil@gmail.com")
                .password("Nowak1")
                .motto("To the death!")
                .userGames(new ArrayList<>(Arrays.asList(2L, 3L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(1L)))
                .build());

        return userList;
    }
}
