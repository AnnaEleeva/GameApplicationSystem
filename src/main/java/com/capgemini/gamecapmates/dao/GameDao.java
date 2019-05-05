package com.capgemini.gamecapmates.dao;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;

import java.util.List;


public interface GameDao<T> {
    List<T> findAll();

    T add(T t);

    T findById(Long id) throws NoSuchGameException;

    void remove(T t);
}
