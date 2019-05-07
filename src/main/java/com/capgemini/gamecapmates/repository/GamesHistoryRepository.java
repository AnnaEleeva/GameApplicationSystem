package com.capgemini.gamecapmates.repository;


import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.GamesHistory;
import com.capgemini.gamecapmates.enums.GameResult;
import org.springframework.stereotype.Repository;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Repository
public class GamesHistoryRepository implements Dao<GamesHistory> {

    private List<GamesHistory> gamesHistoryList;

    public GamesHistoryRepository() {
        gamesHistoryList = new ArrayList<>();

        gamesHistoryList.add(GamesHistory.builder()
                .id(1L)
                .idGame(1L)
                .date(LocalDate.of(2018, 1, 2))
                .action("Rozegrana gra Warcraft z uzytkownikiem MB")
                .players(new ArrayList<>(Arrays.asList(2L,3L)))
                .gameResult(GameResult.WIN)
                .rating(3.5)
                .build());
        gamesHistoryList.add(GamesHistory.builder()
                .id(2L)
                .idGame(2L)
                .date(LocalDate.of(2018, 11, 12))
                .action("Rozegrana gra z uzytkownikami Joanna Nowak i Alicja Curus")
                .players(new ArrayList<>(Arrays.asList(1L, 3L)))
                .gameResult(GameResult.LOSE)
                .rating(5.0).build());
        gamesHistoryList.add(GamesHistory.builder()
                .id(3L)
                .idGame(3L)
                .date(LocalDate.of(2018, 10, 22))
                .action("Rozegrana gra Lich King z Tomek Nowak")
                .players(new ArrayList<>(Arrays.asList(2L, 1L)))
                .gameResult(GameResult.DRAW)
                .rating(5.0).build());

    }

    @Override
    public List<GamesHistory> findAll() {
        return gamesHistoryList.stream()
                .filter(Objects::nonNull)
                .collect(toList());
    }

    @Override
    public GamesHistory add(GamesHistory gamesHistory) {
        if (gamesHistory != null) {
            gamesHistoryList.add(gamesHistory);

            return gamesHistory;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public GamesHistory findById(Long id) throws NoSuchUserException {
        if (id != null) {
            return gamesHistoryList.stream()
                    .filter(gamesHistory -> gamesHistory.getId().equals(id))
                    .findAny().orElseThrow(NoSuchUserException::new);
        }
        throw new NoSuchUserException();
    }

    @Override
    public void remove(GamesHistory gamesHistory) {
        gamesHistoryList.remove(gamesHistory);
    }

    @Override
    public GamesHistory edit(GamesHistory gamesHistory) {
        return null;
    }

    @Override
    public void clear() {
        gamesHistoryList.clear();
    }

}

