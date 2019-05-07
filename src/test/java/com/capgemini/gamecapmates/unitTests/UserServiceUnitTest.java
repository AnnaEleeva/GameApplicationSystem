package com.capgemini.gamecapmates.unitTests;

import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private GameRepository gameRepository;


}
