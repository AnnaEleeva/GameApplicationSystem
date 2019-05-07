package com.capgemini.gamecapmates.unitTests.mapper.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.StatisticsDto;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicUserInformationServiceTest {

    private BasicUserInformationService basicUserInformationService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
       // when(userRepository.add(any(User.class))).thenReturn();
    }


}
