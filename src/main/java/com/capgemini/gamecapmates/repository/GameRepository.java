package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.Game;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class GameRepository implements Dao<Game> {
    private List<Game> gameList;

    public GameRepository(){
        gameList= new ArrayList<>();

        gameList.add(new Game(1L, "Warcraft", LocalDate.of(2018, 12, 11), 2, 2,"Searching for players with skill"));
        gameList.add(new Game(2L,"Wolfenstein",LocalDate.of(2018,1,1),2,5,""));
        gameList.add(new Game(3L,"Wolfenstein: New Castle",LocalDate.of(2018,11,14),6,5,""));
        gameList.add(new Game(4L,"Lich King",LocalDate.of(2018,7,17),3,1,""));
        gameList.add(new Game(5L,"Synthetik",LocalDate.of(2018,6,21),4,2,""));
    }

    @Override
    public List<Game> findAll() {
        return gameList;
    }

    @Override
    public Game add(Game game)  {
        if (game != null) {
            gameList.add(game);

            return game;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Game findById(Long id) throws NoSuchGameException { // validate
        if(id!=null) {
            return gameList.stream()
                    .filter(game -> game.getId().equals(id))
                    .findAny().orElse(null);
        } throw new NoSuchGameException();
    }

    @Override
    public void remove(Game game) {
        Predicate<Game> condition= user1 -> user1.equals(game);
        gameList.removeIf(condition);
        System.out.println(gameList);
    }
}
