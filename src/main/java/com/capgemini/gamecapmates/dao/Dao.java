package com.capgemini.gamecapmates.dao;


import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {
    List<T> findAll();

    T save(T t);

    T findById(Long id) throws NoSuchUserException;

    void remove(T t);

}
