package com.capgemini.gamecapmates.dao;

import lombok.NoArgsConstructor;

import java.util.*;

//basic method
@NoArgsConstructor
public abstract class MainDao<T> implements Dao {
    private Map<Integer, T> mapDao;

    protected void setMap (Map<Integer, T> mapDao){
        this.mapDao=mapDao;
    }
    @Override
    public Map<Integer, T> findAll() {
        return mapDao;
    }

    @Override
    public void save(Integer key, Object t) {
        mapDao.put(key, (T) t);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(mapDao.get(Math.toIntExact(id)));
    }

    @Override
    public void deleteById(Object t) {
        mapDao.remove(t);
    }
}
