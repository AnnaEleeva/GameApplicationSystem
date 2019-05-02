package com.capgemini.gamecapmates.dao;

import com.capgemini.gamecapmates.domain.User;
import lombok.NoArgsConstructor;

import java.util.*;

//basic method
@NoArgsConstructor
public abstract class MainDao<T> implements Dao {
    private List<T> listDao;

    protected void setList (List<T> listDao){
        this.listDao=listDao;
    }

    @Override
    public List<T> findAll() {
        return listDao;
    }

    @Override
    public void save(Object t) {
        listDao.add((T) t);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(listDao.get(Math.toIntExact(id)));
    }

    @Override
    public void deleteById(Object t) {
        listDao.remove((T)t);
    }
}
