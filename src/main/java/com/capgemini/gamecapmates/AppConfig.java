package com.capgemini.gamecapmates;

import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    BasicUserInformationService userService(UserRepository userRepository, UserMapper userMapper) {
        return new BasicUserInformationService(userRepository, userMapper);
    }

    @Bean
    BoardGamesService boardGamesService(GameMapper gameMapper, GameRepository gameRepository, UserRepository userRepository) {
        return new BoardGamesService(gameMapper, gameRepository, userRepository);
    }

}
