package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.MainDao;
import com.capgemini.gamecapmates.domain.User;

import java.util.HashMap;
import java.util.Map;

import static com.capgemini.gamecapmates.domain.Level.CAN_I_PLAY_DADDY;
import static com.capgemini.gamecapmates.domain.Level.DO_NOT_HURT_ME;

public class UserRepository extends MainDao<User> {

    Map<Integer, User> userMap;

    public UserRepository() {
        userMap = new HashMap<>();
        userMap.put(new User(1l, , "Jan", "Kowalski", "Jan_Kowalski@gmail.com", "1234", "This is sparta", CAN_I_PLAY_DADDY));
        userMap.put(new User(2l, , "Marcin", "Nowak", "Marcin1@gmail.com", "Nowak1", "To the death!", DO_NOT_HURT_ME));
        userMap.put(new User(3l, , "Adam", "Bak", "Adas2@onet.pl", "Bak1", "Dont die", CAN_I_PLAY_DADDY));

        setMap(userMap);
    }

    // poczatkowe implementacje nowych metod
}
