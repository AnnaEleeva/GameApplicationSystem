package com.capgemini.gamecapmates.dao;

import java.util.Map;
import java.util.Optional;

public interface Dao <T> {

    Map<Integer,T> findAll();

    void save(Integer key,T t);

    Optional<T> findById(Long id);

    void deleteById(T t);

}
