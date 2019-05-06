package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.enums.Category;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class GameRepository implements Dao<Game> {
    private List<Game> gameList;

    public GameRepository(){
        gameList= new ArrayList<>();

        gameList.add(Game.builder()
                .id(1L)
                .name("Warcraft")
                .year_of_publishment(LocalDate.of(2018, 12, 11))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(4)
                .category(Category.RPG)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Wolfenstein: New Castle")
                .year_of_publishment(LocalDate.of(2018, 1, 1))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(5)
                .category(Category.STRATEGY)
                .description("Searching for players with skill")
                .build());
        gameList.add(Game.builder()
                .id(1L)
                .name("Lich King")
                .year_of_publishment(LocalDate.of(2018, 7, 17))
                .minNumberOfPlayers(2)
                .maxNumberOfplayers(6)
                .category(Category.CARD)
                .description("Searching for players with skill")
                .build());
    }

    @Override
    public List<Game> findAll() {
        return gameList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
    }

    @Override
    public Game edit(Game game) {
        return null;
    }
}
