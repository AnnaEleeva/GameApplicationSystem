package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.repository.UserRepository;

public class BasicUserInformationService {

    private UserRepository userRepository;

    public BasicUserInformationService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
}
