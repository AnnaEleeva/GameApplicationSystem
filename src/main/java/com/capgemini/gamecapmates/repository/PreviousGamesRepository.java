package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.PreviousGames;

import java.util.ArrayList;
import java.util.List;

public class PreviousGamesRepository implements Dao<PreviousGames> {

    List<PreviousGames> previousGamesList;

    public PreviousGamesRepository(){
        previousGamesList= new ArrayList<>();

    }
    @Override
    public List<PreviousGames> findAll() {
        return null;
    }

    @Override
    public PreviousGames save(PreviousGames previousGames) {
        return null;
    }

    @Override
    public PreviousGames findById(Long id) throws NoSuchUserException {
        return null;
    }

    @Override
    public void remove(PreviousGames previousGames) {

    }
}
