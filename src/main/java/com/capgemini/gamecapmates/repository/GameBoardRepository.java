package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.GameBoard;

import java.util.*;

public class GameBoardRepository implements Dao<GameBoard> {

    private Set<GameBoard> gameBoardList;

    public GameBoardRepository(GameRepository gameRepository, UserRepository userRepository){

        gameBoardList.add(new GameBoard(1L, gameRepository.findById(1L), userRepository.findById(1L)));
        gameBoardList.add(new GameBoard(2L, gameRepository.findById(1L), userRepository.findById(2L)));
        gameBoardList.add(new GameBoard(3L, gameRepository.findById(3L), userRepository.findById(1L)));
        gameBoardList.add(new GameBoard(4L, gameRepository.findById(3L), userRepository.findById(2L)));
    }

    @Override
    public List<GameBoard> findAll() {
        return null;
    }

    @Override
    public GameBoard save(GameBoard gameBoard) {
        return null;
    }

    @Override
    public Optional<GameBoard> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(GameBoard gameBoard) {

    }
}
