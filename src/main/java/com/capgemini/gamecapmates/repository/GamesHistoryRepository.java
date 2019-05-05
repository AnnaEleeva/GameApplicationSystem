package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.GamesHistory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GamesHistoryRepository implements Dao<GamesHistory> {

   private List<GamesHistory> gamesHistoryList;

    public GamesHistoryRepository() {
        gamesHistoryList = new ArrayList<>();

        gamesHistoryList.add(new GamesHistory(2L, LocalDate.of(2018, 1,2), "Rozegrana gra Warcraft z uzytkownikiem MB","Przegrana",3.5));
        gamesHistoryList.add(new GamesHistory(2L, LocalDate.of(2018, 11,12), "Rozegrana gra z uzytkownikami Joanna Nowak i Alicja Curus","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(3L, LocalDate.of(2018, 10,22), "Rozegrana gra z uzytkownikiem Kamil Bober","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(4L, LocalDate.of(2018, 7,12), "Rozegrana gra Lich King z Tomek Nowak","Wygrana",5.0));
        gamesHistoryList.add(new GamesHistory(5L, LocalDate.of(2018, 11,5), "Potyczka w Synthetik z Bartlomiej Kalosz","Przegrana",3.5));
    }

    @Override
    public List<GamesHistory> findAll() {
        return null;
    }

    @Override
    public GamesHistory save(GamesHistory gamesHistory) {
        return null;
    }

    @Override
    public GamesHistory findById(Long id) {
        return null;
    }

    @Override
    public void remove(GamesHistory gamesHistory) {

    }
}
