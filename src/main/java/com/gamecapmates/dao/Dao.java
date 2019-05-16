package com.gamecapmates.dao;


import com.gamecapmates.Exceptions.NoSuchGameException;
import com.gamecapmates.Exceptions.NoSuchUserException;

import java.util.List;

public interface Dao <T> {
    List<T> findAll();

    T add(T t);

    T findById(Long id) throws NoSuchGameException, NoSuchUserException;

    void remove(T t);

    T edit(T t) throws NoSuchUserException;

    void clear();
}
