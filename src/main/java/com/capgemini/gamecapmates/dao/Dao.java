package com.capgemini.gamecapmates.dao;


import java.util.List;
import java.util.Optional;

public interface Dao <T> {
    List<T> findAll();

    T save(T t);

    Optional<T> findById(Long id);

    void deleteById(T t);

}
