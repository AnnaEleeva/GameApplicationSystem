package com.capgemini.gamecapmates.validation;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import org.springframework.stereotype.Component;

@Component
public class GamesValidator {

    // add constructor check if is any game in repo or contains !!!

    public void checkIfUserGameIdIsNull(Long gameId) throws NoSuchGameException {
        if(gameId == null){
            throw new NoSuchGameException();
        }
    }

    public void checkIfUserGameDtoNull(GameDto gameDto) throws NoSuchGameException{
        if(gameDto == null){
            throw new NoSuchGameException();
        }
    }
    public void checkIfUserGameIsNull(Game game) throws NoSuchGameException{
        if(game == null){
            throw new NoSuchGameException();
        }
    }
}
