package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository
public class UserRepository implements Dao<User> {
    private List< User> userList;

    public UserRepository(){
        userList = new ArrayList<>();

        userList.add(User.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L,4L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L,2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build());
        userList.add(User.builder()
                .id(2L)
                .firstName("Marcin")
                .lastName("Nowak")
                .email("Marcin1@gmail.com")
                .password("Nowak1")
                .motto("To the death!")
                .userGames(new ArrayList<>(Arrays.asList(2L,4L,3L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(1L)))
                .build());
        userList.add(User.builder()
                .id(3L)
                .firstName("Adam")
                .lastName("Bak")
                .email("Adas2@onet.pl")
                .password("Bak1")
                .motto("Dont die")
                .userGames(new ArrayList<>(Arrays.asList(1L,4L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(3L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(3L)))
                .build());

    }

    @Override
    public List<User> findAll(){
        return userList;
    }

    @Override
    public User add(User user) {
        if (user != null && !userList.contains(user)) {
            userList.add(user);
            return user;
        }
        return null;
    }

    @Override
    public User findById(Long id) throws NoSuchUserException {
        if(id!=null){
      return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny().orElse(null);}
        throw new NoSuchUserException();
    }

    @Override
    public void remove(User user) {
        Predicate<User> condition= user1 -> user1.equals(user);
        userList.removeIf(condition);
        System.out.println(userList);
    }
}
