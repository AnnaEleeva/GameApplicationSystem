package com.gamecapmates.mapper;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.domain.User;
import com.gamecapmates.dto.UserDto;
import com.gamecapmates.dto.UserUpdateDto;
import com.gamecapmates.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private UserValidator userValidator;

    public User mapDtoToEntity(final UserDto userDto)  {
            return User.builder()
                    .id(userDto.getId())
                    .age(userDto.getAge())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .motto(userDto.getMotto())
                    .userGames(userDto.getUserGames())
                    .userGamesHistory(userDto.getUserGamesHistory())
                    .userAvailabilityHours(userDto.getUserAvailabilityHours())
                    .build();
    }

    public UserDto mapEntityToDto(final User user)  {

            return UserDto.builder()
                    .id(user.getId())
                    .age(user.getAge())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .motto(user.getMotto())
                    .userGames(user.getUserGames())
                    .userGamesHistory(user.getUserGamesHistory())
                    .userAvailabilityHours(user.getUserAvailabilityHours())
                    .build();

    }

    public User mapUserUpdateDtoToUser(UserUpdateDto userUpdate) throws NoSuchUserException {
       userValidator.checkIfUserUpdateIsNull(userUpdate);
            return User.builder()
                    .id(userUpdate.getId())
                    .firstName(userUpdate.getFirstName())
                    .lastName(userUpdate.getLastName())
                    .email(userUpdate.getEmail())
                    .password(userUpdate.getPassword())
                    .age(calculateAge(userUpdate.getBirthDate()))
                    .motto(userUpdate.getMotto())
                    .build();

    }

    public UserDto mapUserUpdateToDto(UserUpdateDto userUpdateDto) throws NoSuchUserException {
        userValidator.checkIfUserUpdateIsNull(userUpdateDto);
        return UserDto.builder()
                .id(userUpdateDto.getId())
                .age(calculateAge(userUpdateDto.getBirthDate()))
                .firstName(userUpdateDto.getFirstName())
                .lastName(userUpdateDto.getLastName())
                .email(userUpdateDto.getEmail())
                .password(userUpdateDto.getPassword())
                .motto(userUpdateDto.getMotto())
                .build();
    }


    private int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public List<UserDto> mapListToDto (List<User> users){
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getAge(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getMotto(),
                        user.getUserGames(),
                        user.getUserGamesHistory(),
                        user.getUserAvailabilityHours()
                )).collect(Collectors.toList());
    }
}

