package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.Game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GameRepository implements Dao<Game> {
    private Long id = 0L;
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
    public Game save(Game game) {
        if (game != null) {
            game.id = getNextId();
            gameList.add(game);

            return game;
        }
        return null;
    }
    private Long getNextId() {
        return ++id;
    }

    @Override
    public Optional<Game> findById(Long id) { // validate
        return gameList.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Game game) {
        Predicate<Game> condition= user1 -> user1.equals(game);
        gameList.removeIf(condition);
        System.out.println(gameList);
    }
    //hasGame()
}
