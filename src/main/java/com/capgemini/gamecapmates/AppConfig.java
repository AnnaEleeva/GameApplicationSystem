package com.capgemini.gamecapmates;

import com.capgemini.gamecapmates.mapper.GameMapper;
import com.capgemini.gamecapmates.repository.GameBoardRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import com.capgemini.gamecapmates.service.BoardGamesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    BasicUserInformationService userService(UserRepository userRepository){
        return new BasicUserInformationService(userRepository);
    }

    @Bean
    BoardGamesService boardGamesService(GameBoardRepository gameBoardRepository){
        return new BoardGamesService(gameBoardRepository);
    }

}
