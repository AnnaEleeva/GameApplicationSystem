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
       /* gamesHistoryList.add(new GamesHistory(2L, LocalDate.of(2018, 1,2), "Rozegrana gra Warcraft z uzytkownikiem MB","Przegrana",3.5));
        gamesHistoryList.add(new GamesHistory(2L, LocalDate.of(2018, 11,12), "Rozegrana gra z uzytkownikami Joanna Nowak i Alicja Curus","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(3L, LocalDate.of(2018, 10,22), "Rozegrana gra z uzytkownikiem Kamil Bober","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(4L, LocalDate.of(2018, 7,12), "Rozegrana gra Lich King z Tomek Nowak","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(5L, LocalDate.of(2018, 11,5), "Potyczka w Synthetik z Bartlomiej Kalosz","Przegrana",3.5)); */
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
