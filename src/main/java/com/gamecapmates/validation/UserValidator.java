package com.gamecapmates.validation;

import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.domain.User;
import com.gamecapmates.dto.StatisticsDto;
import com.gamecapmates.dto.UserDto;
import com.gamecapmates.dto.UserUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserValidator {


    public void checkIfUserUpdateBirthDateIsSmaller(UserUpdateDto userUpdateDto) {
        if(userUpdateDto.getBirthDate().compareTo(LocalDate.now())> 0){
            throw new IllegalArgumentException();
        }
    }

    public void checkIfUserDtoIsNull(UserDto userDto) throws NoSuchUserException {
        if (userDto == null) {
            throw new NoSuchUserException();
        }
    }

    public void checkIfUserDtoComponentIsNull(UserDto userDto) throws NoSuchUserException { // or instanceof
        if (userDto.getFirstName() == null || userDto.getLastName() == null
                || userDto.getAge() == 0 || userDto.getPassword() == null
                || userDto.getEmail() == null) {
            throw new NoSuchUserException();
        }
    }

    public void checkIfUserUpdateIsNull(UserUpdateDto userUpdateDto) throws NoSuchUserException {
        if (userUpdateDto == null) {
            throw new NoSuchUserException();
        }
    }

    public void checkIfUserIdIsNull(Long userId) throws NoSuchUserException{
        if(userId== null){
            throw new NoSuchUserException();
        }
    }

    public void checkIfStatisticsUserIsNull(StatisticsDto statisticsDto) throws NoSuchUserException{
        if(statisticsDto == null){
            throw new NoSuchUserException();
        }
    }

    public void checkIfStatisticsComponentIsDifferentThanNull(StatisticsDto statisticsDto) throws NoSuchUserException{
        if(statisticsDto.getLevel()== null && statisticsDto.getRankingPosition()==null){
            throw new NoSuchUserException();
        }
    }

    public void checkIfUserIsNull(User user)throws NoSuchUserException{
        if(user==null){
            throw new NoSuchUserException();
        }
    }

}
