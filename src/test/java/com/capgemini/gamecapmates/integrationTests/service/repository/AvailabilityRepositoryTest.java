package com.capgemini.gamecapmates.integrationTests.service.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class AvailabilityRepositoryTest {


    private AvailabilityRepository availabilityRepository;

    @Before
    public void setUp(){
        availabilityRepository= new AvailabilityRepository();
    }

    @Test
    public void shouldReturnEntityWhenGetById() {
        //given
       Availability availability= Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build();
        //when
        Availability result = availabilityRepository.findById(1L);
        //then
        assertEquals(availability, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdNotExistOnList() {
        //given
        //when
        availabilityRepository.findById(9L);
    }

    @Test
    public void shouldReturnAvailabilityListWhenGetAll() {
        //given
        List<Availability> expectedList = generateList();
        //when
        List<Availability> result = availabilityRepository.findAll();
        //then
        assertEquals(expectedList, result);
    }

    @Test
    public void shouldReturnAddedElementAndAddToList() {
        //given
        Availability availability= Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build();
        availability.setId(5L);
        //when
        Availability result = availabilityRepository.add(availability);
        //then
        assertEquals(availability, result);
        assertEquals(availability, availabilityRepository.findById(5L));
    }

    @Test
    public void shouldReturnUpdatedElementWhenUpdate() throws NoSuchUserException {
        //given
        String expectedInfo = "RIP";
        Availability availability= Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information(expectedInfo)
                .build();
        //when
        Availability result = availabilityRepository.edit(availability);
        String resultInfo = availabilityRepository.findById(1L).getInformation();
        //then
        assertEquals(availability, result);
        assertEquals(expectedInfo, resultInfo);
    }

    @Test
    public void shouldReturnDeletedElement() {
        //given
        Availability availability= Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build();
        //when
         availabilityRepository.remove(availability);
        //then
        assertEquals(Long.valueOf(2L), availabilityRepository.findAll().get(0).getId());
    }

    public List<Availability> generateList(){
       List<Availability> availabilityList= new ArrayList<>();

        availabilityList.add(Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build());
        availabilityList.add(Availability.builder()
                .id(2L)
                .dateFrom(LocalDateTime.of(2019, 2, 1, 12, 0))
                .dateTo(LocalDateTime.of(2019, 2, 1, 14, 0))
                .disponibility(Disponibility.BUSY)
                .information("Playing new Game on steam")
                .build());
        availabilityList.add(Availability.builder()
                .id(3L)
                .dateFrom(LocalDateTime.of(2019, 6, 20, 12, 0))
                .dateTo(LocalDateTime.of(2019, 6, 20, 13, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("PVP..")
                .build());

        return availabilityList;
    }

}
