package com.capgemini.gamecapmates.dao;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameDao<T> {
    List<T> findAll();

    Optional<Game> save(T t);

    Optional<T> findById(Long id) throws NoSuchGameException;

    void remove(T t);

    Optional<T> findByName(String name) throws NoSuchGameException;
}
