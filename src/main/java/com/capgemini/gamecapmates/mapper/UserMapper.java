package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.domain.UserDto;

public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .motto(userDto.getMotto())
                .level(userDto.getLevel())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .motto(user.getMotto())
                .level(user.getLevel())
                .build();
    }
}
