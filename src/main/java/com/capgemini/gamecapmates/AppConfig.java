package com.capgemini.gamecapmates;

import com.capgemini.gamecapmates.mapper.AvailabilityMapper;
import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.mapper.HistoryGameMapper;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.GamesHistoryRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.AvailabilityService;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import com.capgemini.gamecapmates.service.UserGamesHistoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    BasicUserInformationService userService(UserRepository userRepository, UserMapper userMapper, GamesHistoryRepository gamesHistoryRepository) {
        return new BasicUserInformationService(userRepository, userMapper, gamesHistoryRepository);
    }

    @Bean
    BoardGamesService boardGamesService(GameMapper gameMapper, GameRepository gameRepository, UserRepository userRepository) {
        return new BoardGamesService(gameMapper, gameRepository, userRepository);
    }

    @Bean
    AvailabilityService availabilityService(AvailabilityRepository availabilityRepository, AvailabilityMapper availabilityMapper, UserRepository userRepository) {
        return new AvailabilityService(availabilityRepository, availabilityMapper, userRepository);
    }

    @Bean
    UserGamesHistoryService userGamesHistoryService(GamesHistoryRepository gamesHistoryRepository, HistoryGameMapper historyGameMapper, UserRepository userRepository) {
        return new UserGamesHistoryService(gamesHistoryRepository, historyGameMapper, userRepository);
    }

    @Bean
    GameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean
    GamesHistoryRepository gamesHistoryRepository() {
        return new GamesHistoryRepository();
    }

    @Bean
    AvailabilityRepository availabilityRepository() {
        return new AvailabilityRepository();
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository();
    }
}
