package com.capgemini.gamecapmates;

import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.service.UserGamesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    UserGamesService userService(UserRepository userRepository){
        return new UserGamesService(userRepository);
    }
}
