package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.User;

import java.util.*;
import java.util.function.Predicate;

import static com.capgemini.gamecapmates.domain.Level.CAN_I_PLAY_DADDY;
import static com.capgemini.gamecapmates.domain.Level.DO_NOT_HURT_ME;

public class UserRepository implements Dao<User> {
    private Long id = 0L;
    private List< User> userList;

    public UserRepository() {
        userList = new ArrayList<>();

        userList.add(new User(1L,1 , "Jan", "Kowalski", "Jan_Kowalski@gmail.com", "1234", "This is sparta", CAN_I_PLAY_DADDY));
        userList.add(new User(2L,1 ,"Marcin", "Nowak", "Marcin1@gmail.com", "Nowak1", "To the death!", DO_NOT_HURT_ME));
        userList.add(new User(3L, 2, "Adam", "Bak", "Adas2@onet.pl", "Bak1", "Dont die", CAN_I_PLAY_DADDY));
    }

    @Override
    public User save(User user) { // validation
        if (user != null) {
            user.id = getNextId();
            userList.add(user);

            return user;
        }
        return null;
    }

    private Long getNextId() {
        return ++id;
    }

    @Override
    public List<User> findAll(){
        return userList;
    }

    @Override
    public Optional<User> findById(Long id){ // validate
      return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(User user) {
        Predicate<User> condition= user1 -> user1.equals(user);
        userList.removeIf(condition);
        System.out.println(userList);
    }
    // zaimplementowac potrzebna metody w repozytorium z dao zeby z bazy danych wywolywac rekordy !! encje !!!!
}
