package com.capgemini.gamecapmates.dao;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;

import java.util.List;


public interface GameBoardDao <T> {
    List<T> findById(Long id) throws NoSuchUserException;

}
