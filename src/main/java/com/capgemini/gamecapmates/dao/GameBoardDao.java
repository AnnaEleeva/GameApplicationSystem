package com.capgemini.gamecapmates.dao;

import java.util.List;

public interface GameBoardDao <T> {
    List<T> findById(Long id);
}
