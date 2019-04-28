package com.capgemini.gamecapmates.facade;

import com.capgemini.gamecapmates.domain.UserDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.service.UserGamesService;

public class UserFacade {

    private UserMapper userMapper;
    private UserGamesService userGamesService;


    public UserFacade(UserMapper userMapper, UserGamesService userGamesService) {
        this.userMapper = userMapper;
        this.userGamesService=userGamesService;
    }

    // logic
    public UserDto updateUserBasicInformation(final UserDto userDto) {
        return userMapper.mapToUserDto(userGamesService.updateUserInformation(userMapper.mapToUser(userDto)));
    }


}
