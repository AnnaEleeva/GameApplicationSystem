package com.capgemini.gamecapmates.repository;


import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.PreviousGames;

import java.util.ArrayList;
import java.util.List;

public class PreviousGamesRepository implements Dao<PreviousGames> {

    private List<PreviousGames> previousGamesList;

    public PreviousGamesRepository(){
        previousGamesList= new ArrayList<>();
    }
    @Override
    public List<PreviousGames> findAll() {
        return previousGamesList;
    }

    @Override
    public PreviousGames add(PreviousGames previousGames) {
        if (previousGames != null) {
            previousGamesList.add(previousGames);

            return previousGames;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public PreviousGames findById(Long id) {
      return null;
    }

    @Override
    public void remove(PreviousGames previousGames) {

    }
}
