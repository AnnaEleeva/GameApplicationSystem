package com.gamecapmates.repository;


import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.dao.Dao;
import com.gamecapmates.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.lang.Math.toIntExact;

@Repository
public class UserRepository implements Dao<User> {
    private List< User> userList;

    public UserRepository(){
        userList = new ArrayList<>();

        userList.add(User.builder()
                .id(1L)
                .age(28)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("Jan_Kowalski@gmail.com")
                .password("1234")
                .motto("This is sparta")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Arrays.asList(1L,2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(2L)))
                .build());
        userList.add(User.builder()
                .id(2L)
                .age(25)
                .firstName("Marcin")
                .lastName("Nowak")
                .email("Marcin1@gmail.com")
                .password("Nowak1")
                .motto("To the death!")
                .userGames(new ArrayList<>(Arrays.asList(2L,3L)))
                .userGamesHistory(new ArrayList<>(Collections.singletonList(2L)))
                .userAvailabilityHours(new ArrayList<>(Collections.singletonList(1L)))
                .build());
        userList.add(User.builder()
                .id(3L)
                .age(25)
                .firstName("Adam")
                .lastName("Bak")
                .email("Adas2@onet.pl")
                .password("Bak1")
                .motto("Dont die")
                .userGames(new ArrayList<>(Arrays.asList(1L,2L)))
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
            userList.add(user);
            return user;
    }

    @Override
    public User findById(Long id) throws NoSuchUserException {
        if (id != null) {
            return userList.stream()
                    .filter(user -> id.equals(user.getId()))
                    .findAny().orElseThrow(NoSuchUserException::new);
        }
        throw new NoSuchUserException();
    }

    @Override
    public void remove(User user) {
        userList.remove(user);

    }

    @Override
    public User edit(User user) throws NoSuchUserException {
        if (user.getId() <= userList.size()) {
            int index = toIntExact(getIndex(user.getId()));
            userList.set(index, user);
            return user;
        }
        throw new NoSuchUserException();
    }

    private Long getIndex(Long id) {
        User user = userList.stream()
                .filter(user1 -> user1.getId().equals(id))
                .findAny().orElse(null);

        return (long) userList.indexOf(user);
    }

    public void clear() {
        userList.clear();
    }
}
