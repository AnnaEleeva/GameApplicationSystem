package com.capgemini.gamecapmates.integrationTests.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.AvailabilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class AvailabilityServiceTest {

    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void editAvailabilityHoursTest() throws NoSuchUserException {
        //given
        AvailabilityDto availabilityToUpdate = AvailabilityDto.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.BUSY)
                .information("Mom wants me to wash dish")
                .build();
        //when
      //  availabilityService.editAvailabilityHours(availabilityToUpdate,1L);
        List<Long> availabilityDto=userRepository.findById(1L).getUserAvailabilityHours();
        //then
       // assertEquals();
    }

}
