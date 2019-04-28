package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.domain.User;

import java.util.List;
import java.util.Optional;

//basic method
public interface UserRepository {

    List<User> findAll();

    User save(User user);

    Optional<User> findById(long id);

    void deleteById(long id);
}
