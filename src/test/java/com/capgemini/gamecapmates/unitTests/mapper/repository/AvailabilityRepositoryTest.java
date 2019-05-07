package com.capgemini.gamecapmates.unitTests.mapper.repository;

import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.enums.Disponibility;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityRepositoryTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @Captor
    private ArgumentCaptor<Availability> availabilityArgumentCaptor;

    @Before
    public void setUp() {

        when(availabilityRepository.add(any(Availability.class)))
                .thenReturn(new Availability(1L, LocalDateTime.of(2019, 4, 29, 12, 0),
                         LocalDateTime.of(2019, 4, 29, 15, 0), Disponibility.AVAILABLE,
                        "Battleground?"));
    }

    @Test
    public void shouldAddAvailability(){
        //given
        final Availability availability= new Availability(1L, LocalDateTime.of(2019, 4, 29, 12, 0),
                LocalDateTime.of(2019, 4, 29, 15, 0), Disponibility.AVAILABLE,
                "Battleground?");
        //when
       Availability availability1= availabilityRepository.add(availability);
        //then
        assertEquals(availability,availability1);

       // verify(availabilityRepository, times(1)).add(availabilityArgumentCaptor.capture());

        assertEquals("Battleground?",availabilityArgumentCaptor.getValue().getInformation());
    }

    @Test
    public void shouldFindAll(){
        //given
        availabilityRepository= new AvailabilityRepository();
        //when
        List<Availability> availabilityList= availabilityRepository.findAll();
        int size=availabilityList.size();
        //then
        assertEquals(3,size);

      //  verify(availabilityRepository, times(1)).add(availabilityArgumentCaptor.capture());
    }

    @Test
    public void shouldFindById(){
        //given
       Availability availability= new Availability(1L, LocalDateTime.of(2019, 4, 29, 12, 0),
                LocalDateTime.of(2019, 4, 29, 15, 0), Disponibility.AVAILABLE,
                "Battleground?");

        Availability availability1= availabilityRepository.add(availability);
        //when
        Availability find= availabilityRepository.findById(1L);
        //then
        assertEquals(availability1,find);
    }
    @Test
    public void shouldNotFindById(){
        //given
        //when
        //then
    }
    @Test
    public void shouldRemove(){
        //given
        //when
        //then
    }
    @Test
    public void shouldEdit(){
        //given
        //when
        //then
    }

    @Test
    public void shouldGetIndex(){
        //given
        //when
        //then
    }

}
