package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository
public class UserRepository implements Dao<User> {
    private List< User> userList;

    public UserRepository() {
        userList = new ArrayList<>();

      //  userList.add(new User(1L,1 , "Jan", "Kowalski", "Jan_Kowalski@gmail.com", "1234", "This is sparta"));
       // userList.add(new User(2L,1 ,"Marcin", "Nowak", "Marcin1@gmail.com", "Nowak1", "To the death!"));
       // userList.add(new User(3L, 2, "Adam", "Bak", "Adas2@onet.pl", "Bak1", "Dont die"));
    }

    @Override
    public User save(User user) {
        if (user != null && !userList.contains(user)) {
            userList.add(user);
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll(){
        return userList;
    }


    @Override
    public User findById(Long id) throws NoSuchUserException { // validate
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
