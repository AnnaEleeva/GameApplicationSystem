package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.UserDto;

import java.time.LocalDate;
import java.time.Period;

public class UserMapper {

    public User mapDtoToEntity(final UserDto userDto) {
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

    public UserDto mapEntityToDto(final User user) {
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

    public UserDto mapUserUpdateToDto(User userUpdate){
        return UserDto.builder()
                .firstName(userUpdate.getFirstName())
                .lastName(userUpdate.getLastName())
                .email(userUpdate.getEmail())
                .password(userUpdate.getPassword())
                .age(calculateAge(userUpdate.getBirthDate(),userUpdate.getCurrentDate()))
                .motto(userUpdate.getMotto())
                .build();

    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

