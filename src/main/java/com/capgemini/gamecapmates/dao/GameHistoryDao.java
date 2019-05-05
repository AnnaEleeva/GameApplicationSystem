package com.capgemini.gamecapmates.dao;

import java.util.List;

public interface GameHistoryDao<T> {
    List<T> findAll();

}
