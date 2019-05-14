package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.controller.AvailabilityRestController;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.restService.AvailabilityWebService;
import com.capgemini.gamecapmates.service.AvailabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AvailabilityRestControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private AvailabilityService availabilityService;

    @InjectMocks
    private AvailabilityRestController availabilityRestController;

    private List<AvailabilityDto> availabilityDtos;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(availabilityRestController).build();
    }

    @Test
    public void shouldGetAvailabilityForUser() throws Exception{
        //given
        //when
        when(availabilityService.getAvailabilityForUser(any(Long.class))).thenReturn(availabilityDtos);
        ResultActions resultActions= mockMvc.perform(get("/api/availabilities/availability/1"));
        //then
        Mockito.verify(availabilityService,times(1)).getAvailabilityForUser(any(Long.class));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(availabilityDtos.get(0).getInformation())));
    }


    @Test
    public void shouldAddAvailabilityToCollection() throws Exception {
        //given
        AvailabilityDto availabilityDto= new AvailabilityDto();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(availabilityDto);
        //when
        //then
        when(availabilityService.addAvailability(availabilityDto)).thenReturn(availabilityDto);
         mockMvc.perform(post("/api/availabilities/addAvailability")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
