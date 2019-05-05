package com.capgemini.gamecapmates.dao;


import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {
    List<T> findAll();

    T add(T t);

    T findById(Long id) throws NoSuchGameException, NoSuchUserException;

    void remove(T t);


}
