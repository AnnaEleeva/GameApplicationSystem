package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.History;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HistoryRepository implements Dao<History> {

    List<History> historyList;

    public HistoryRepository(){
        historyList.add(new History(1L, LocalDate.of(2018, 1,2), "Rozegrana gra Warcraft z uzytkownikiem MB","Przegrana",3.5));
        historyList.add(new History(2L, LocalDate.of(2018, 11,12), "Rozegrana gra z uzytkownikami Joanna Nowak i Alicja Curus","Wygrana",5.0));
        historyList.add(new History(3L, LocalDate.of(2018, 10,22), "Rozegrana gra z uzytkownikiem Kamil Bober","Wygrana",5.0));
        historyList.add(new History(4L, LocalDate.of(2018, 7,12), "Rozegrana gra Lich King z Tomek Nowak","Wygrana",5.0));
        historyList.add(new History(5L, LocalDate.of(2018, 11,5), "Potyczka w Synthetik z Bartlomiej Kalosz","Przegrana",3.5));
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    @Override
    public History save(History history) {
        return null;
    }

    @Override
    public Optional<History> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(History history) {

    }
}
