package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.MainDao;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.GameBoard;
import com.capgemini.gamecapmates.domain.Level;
import com.capgemini.gamecapmates.domain.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.capgemini.gamecapmates.domain.Level.CAN_I_PLAY_DADDY;
import static com.capgemini.gamecapmates.domain.Level.DO_NOT_HURT_ME;

public class GameBoardRepository extends MainDao<GameBoard> {

    Map<Integer,GameBoard> gameBoardMap;

    public GameBoardRepository(){

        User user1=new User(1l,,"Jan","Kowalski","Jan_Kowalski@gmail.com","1234","This is sparta",CAN_I_PLAY_DADDY);
        User user2=new User(2l,,"Marcin","Nowak","Marcin1@gmail.com","Nowak1","To the death!",DO_NOT_HURT_ME);
        User user3=new User(3l,,"Adam","Bak","Adas2@onet.pl","Bak1","Dont die",CAN_I_PLAY_DADDY);

        Game game1 = new Game(1l, "Warcraft", LocalDate.of(2018, 12, 11), 2, 2, "Searching for players with skill");
        Game game2 = new Game(2l, "Wolfenstein", LocalDate.of(2018, 01, 01), 2, 5, "");
        Game game3 = new Game(3l, "Wolfenstein: New Castle", LocalDate.of(2018, 11, 14), 6, 5, "");
        Game game4 = new Game(4l, "Lich King", LocalDate.of(2018, 07, 17), 3, 1, "");
        Game game5 = new Game(5l, "Synthetik", LocalDate.of(2018, 06, 21), 4, 2, "");

        Map<User,Game> map1= new HashMap<>();

        map1.put(user1,game2);
        map1.put(user2, game1);
        map1.put(user3, game3);
        map1.put(user1,game4);
        map1.put(user3,game5);

        gameBoardMap = new HashMap<>();

        gameBoardMap.put(1,new GameBoard(map1));

        setMap(gameBoardMap);
    }
}
