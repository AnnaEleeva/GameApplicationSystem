package com.capgemini.gamecapmates.restService;

import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.dto.GameDto;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Service
public class BoardGamesWebService {

    private GameRepository gameRepository;
    private GameMapper gameMapper;

    @Autowired
    public BoardGamesWebService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    public List<GameDto> searchGame(GameDto gameDto) {
        List<Game> allGames = gameRepository.findAll();
        List<GameDto> allGamesDto = allGames.stream()
                .map(entity -> gameMapper.mapEntityToDto(entity))
                .collect(Collectors.toList());

        Predicate<GameDto> name = gameDto.getName() == null || gameDto.getName().length() == 0 ?
                gameDto1 -> gameDto1.getName().equalsIgnoreCase(gameDto.getName()) : gameDto1 -> true;
        Predicate<GameDto> year = gameDto.getYear_of_publishment() == null ?
                gameDto1 -> gameDto1.getYear_of_publishment().equals(gameDto.getYear_of_publishment()) : gameDto1 -> true;
        Predicate<GameDto> category = gameDto.getCategory() == null ?
                gameDto1 -> gameDto1.getCategory().equals(gameDto.getCategory()) : gameDto1 -> true;

        List<GameDto> result = allGamesDto.stream()
                .filter(name)
                .filter(year)
                .filter(category)
                .collect(Collectors.toList());
        return result;
    }
}
