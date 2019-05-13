package com.capgemini.gamecapmates.controllerTests;

import com.capgemini.gamecapmates.controller.AvailabilityRestController;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.restService.AvailabilityWebService;
import com.capgemini.gamecapmates.service.AvailabilityService;
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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AvailabilityRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AvailabilityService availabilityService;

    @Mock
    private AvailabilityWebService availabilityWebService;

    @InjectMocks
    private AvailabilityRestController availabilityRestController;

    private List<AvailabilityDto> availabilityDtos;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(availabilityRestController).build();
        availabilityDtos= generateList();
    }
// jak zrobic zeby wartosci byly te same?
    @Test
    public void shouldEditAvailabilityHours() throws Exception{
        //given
       AvailabilityDto availabilityDto= AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.BUSY)
                .information("Cant play")
                .build();
        //when
        when(availabilityService.editAvailabilityHours(availabilityDto)).thenReturn(availabilityDto);
        ResultActions resultActions = mockMvc.perform(get("/api/availability/edit"));
        //then
        Mockito.verify(availabilityService,times(1)).editAvailabilityHours(availabilityDto);
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].information", is(availabilityDto.getInformation())));
    }
    @Test
    public void shouldNotEditAvailabilityHours() throws Exception{
        //given
        AvailabilityDto availabilityDto= null;
        //when
        when(availabilityService.editAvailabilityHours(availabilityDto)).thenReturn(availabilityDto);
        ResultActions resultActions = mockMvc.perform(get("/api/availability/edit"));
        //then
        resultActions.andExpect(status().isNotFound());
    }
    @Test
    public void shouldGetAvailabilityForUser() throws Exception{
        //given
        when(availabilityService.getAvailabilityForUser(1L)).thenReturn(availabilityDtos);
        ResultActions resultActions= mockMvc.perform(get("/api/availability/1"));
        //then
        Mockito.verify(availabilityService,times(1)).getAvailabilityForUser(1L);
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].information", is(availabilityDtos.get(0).getInformation())));
    }
    @Test
    public void shouldNotGetAvailabilityForUser() throws Exception{
        //given
        //when
        when(availabilityService.getAvailabilityForUser(1L)).thenReturn(availabilityDtos);
        ResultActions resultActions= mockMvc.perform(get("/api/availability/10"));
        //then
    }

    @Test
    public void shouldSearchForAvailability() throws Exception{
        //given
        List<AvailabilityDto> availabilityDtos= new ArrayList<>();
        AvailabilityDto availabilityDto= AvailabilityDto.builder()
                .id(2L)
                .dateFrom(LocalDateTime.of(2019, 2, 1, 12, 0))
                .dateTo(LocalDateTime.of(2019, 2, 1, 14, 0))
                .disponibility(Disponibility.BUSY)
                .information("Playing new Game on steam")
                .build();
        availabilityDtos.add(availabilityDto);
        //when
        when(availabilityWebService.findAvailabilityAfterParam(availabilityDtos.get(1))).thenReturn(availabilityDtos);
        ResultActions resultActions= mockMvc.perform(get("/api/availability/search"));
        //then
        Mockito.verify(availabilityWebService,times(1)).findAvailabilityAfterParam(availabilityDtos.get(1));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].information", is(availabilityDtos.get(1).getInformation())));
    }

    @Test
    public void shouldNotSearchForAvailability() throws Exception{
        List<AvailabilityDto> availabilityDtos= new ArrayList<>();
        AvailabilityDto availabilityDto= AvailabilityDto.builder()
                .id(2L)
                .dateFrom(LocalDateTime.of(2019, 2, 1, 12, 0))
                .dateTo(LocalDateTime.of(2019, 2, 1, 14, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Playing new Game on steam")
                .build();
        availabilityDtos.add(availabilityDto);
        //when
        when(availabilityWebService.findAvailabilityAfterParam(availabilityDtos.get(1))).thenReturn(availabilityDtos);
        ResultActions resultActions= mockMvc.perform(get("/api/availability/search"));
        //then
        resultActions.andExpect(status().isNotFound());
    }

    private List<AvailabilityDto> generateList() {
        List<AvailabilityDto> availabilityList = new ArrayList<>();

        availabilityList.add(AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build());
        availabilityList.add(AvailabilityDto.builder()
                .id(2L)
                .dateFrom(LocalDateTime.of(2019, 2, 1, 12, 0))
                .dateTo(LocalDateTime.of(2019, 2, 1, 14, 0))
                .disponibility(Disponibility.BUSY)
                .information("Playing new Game on steam")
                .build());

        return availabilityList;
    }
}
