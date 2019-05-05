package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.GameBoardDao;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.GameBoard;
import com.capgemini.gamecapmates.dto.GameDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class GameBoardRepository  {

    private List<GameBoard> gameBoardList;

   /* public GameBoardRepository(GameRepository gameRepository, UserRepository userRepository) throws NoSuchUserException, NoSuchGameException {

        gameBoardList = new ArrayList<>();

        gameBoardList.add(new GameBoard(gameRepository.findById(1L), userRepository.findById(1L)));
        gameBoardList.add(new GameBoard(gameRepository.findById(1L), userRepository.findById(2L)));
        gameBoardList.add(new GameBoard(gameRepository.findById(3L), userRepository.findById(1L)));
        gameBoardList.add(new GameBoard(gameRepository.findById(3L), userRepository.findById(3L)));
        gameBoardList.add(new GameBoard(gameRepository.findById(4L), userRepository.findById(3L)));
        gameBoardList.add(new GameBoard(gameRepository.findById(5L), userRepository.findById(2L)));
}

   // @Override
  //  public List<Game> findById(Long userId) {
   //         return gameBoardList.stream()
    //                .filter(gameBoard -> gameBoard.getUserBoard().get().getId().equals(userId))
    //                .map(gameBoard -> gameBoard.getGameBoard()
     //               .collect(Collectors.toList());

    */
    }

