package com.gamecapmates.unitTests;

import com.gamecapmates.mapper.UserMapper;
import com.gamecapmates.repository.GameRepository;
import com.gamecapmates.repository.UserRepository;
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
