package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.controller.UserRestController;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.restService.BasicUserInformationWebService;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
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
public class UserRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BasicUserInformationService basicUserInformationService;

    @Mock
    private BasicUserInformationWebService basicUserInformationWebService;

    @InjectMocks
    private UserRestController userRestController;

    private List<UserDto> userDtos;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(userRestController).build();
        userDtos= generateList();
    }

    @Test
    public void shouldGetAllUser(){
        //given
        //when
        //then
    }

    @Test
    public void shouldGetUserFromCollection(){
        //given
        //when
        //then
    }

    @Test
    public void shouldNotGetUserFromCollection(){
        //given
        //when
        //then
    }

    @Test
    public void shouldUpdateUser(){
        //given
        //when
        //then
    }

    @Test
    public void shouldNotUpdateUser(){
        //given
        //when
        //then
    }

    @Test
    public void shouldSearchForUser(){
        //given
        //when
        //then
    }

    @Test
    public void shouldNotSearchForUser(){
        //given
        //when
        //then
    }

    private List<UserDto> generateList(){
        return null;
    }
}
