package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.AvailabilityException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.AvailabilityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class AvailabilityServiceTest {

    @Autowired
    private AvailabilityService availabilityService;
    @Autowired
    private UserRepository userRepository;

    private AvailabilityRepository availabilityRepository;

    @Before
    public void setUp() {
        availabilityRepository = new AvailabilityRepository();
    }

    @Test
    public void getAvailabilityForUserTest() throws AvailabilityException, NoSuchUserException {
        //given
        //when
        AvailabilityDto availabilityDto=availabilityService.getAvailabilityForUser(1L).stream()
                .findAny().orElse(null);
        AvailabilityDto availability= availabilityService.getAvailabilityById(1L);
        //then
        assertEquals(availability,availabilityDto);
    }

    @Test
    public void addAvailabilityHoursToUserTest() throws NoSuchUserException, AvailabilityException {
        //given
        AvailabilityDto availabilityToUpdate = AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.BUSY)
                .information("Rip")
                .build();
        //when
         availabilityService.addAvailabilityHours(availabilityToUpdate, 1L);
        //then
        assertEquals(2, userRepository.findById(1L).getUserAvailabilityHours().size());

    }

    @Test
    public void declineAvailabilityTest() throws AvailabilityException, NoSuchUserException {
        //given
        String info = "RIP";
        AvailabilityDto availabilityToUpdate = AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.BUSY)
                .information(info)
                .build();

        //when
        AvailabilityDto availabilityDto = availabilityService.declineAvailability(1L, info);
        AvailabilityDto availabilityDto1=availabilityService.getAvailabilityById(1L);
        //then
        assertEquals(availabilityToUpdate, availabilityDto);
        assertEquals(availabilityToUpdate,availabilityDto1);
    }

    @Test
    public void editAvailabilityHoursTest() throws NoSuchUserException, AvailabilityException {
        //given
        String expectedInfo = "RIP";
        AvailabilityDto availabilityToUpdate = AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.BUSY)
                .information(expectedInfo)
                .build();
        //when
        AvailabilityDto availabilityDto= availabilityService.editAvailabilityHours(availabilityToUpdate);
        String reuslt=availabilityService.getAvailabilityById(1L).getInformation();
        //then
        assertEquals(availabilityDto,availabilityToUpdate);
        assertEquals(expectedInfo,reuslt);
    }

    @Test
    public void removeAvailabilityHoursTest() throws NoSuchUserException, AvailabilityException {
        //given
        //when
        availabilityService.removeAvailabilityHours(1L,2L);
        //then
        assertEquals(0,userRepository.findById(1L).getUserAvailabilityHours().size());
    }

}
