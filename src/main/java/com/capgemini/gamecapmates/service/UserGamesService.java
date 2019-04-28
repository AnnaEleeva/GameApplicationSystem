package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.repository.UserRepository;

public class UserGamesService {

    private UserRepository userRepository;

    public UserGamesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUserInformation(final User user){
      return userRepository.save(user);
    }

}
