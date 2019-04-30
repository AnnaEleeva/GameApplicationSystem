package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.dao.MainDao;
import com.capgemini.gamecapmates.domain.Game;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GameRepository extends MainDao<Game> {

    Map<Integer,Game> gameMap;

    public GameRepository(){
        gameMap= new HashMap<>();

        gameMap.put(1,new Game(1l, "Warcraft", LocalDate.of(2018, 12, 11), 2, 2,"Searching for players with skill"));
        gameMap.put(2,new Game(2l,"Wolfenstein",LocalDate.of(2018,01,01),2,5,""));
        gameMap.put(3,new Game(3l,"Wolfenstein: New Castle",LocalDate.of(2018,11,14),6,5,""));
        gameMap.put(4,new Game(4l,"Lich King",LocalDate.of(2018,07,17),3,1,""));
        gameMap.put(5,new Game(5l,"Synthetik",LocalDate.of(2018,06,21),4,2,""));

        setMap(gameMap);
    }
}
