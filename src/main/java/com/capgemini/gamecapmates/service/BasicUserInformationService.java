package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dao.MainDao;
import com.capgemini.gamecapmates.repository.UserRepository;

import java.util.List;
import java.util.Map;

public class BasicUserInformationService extends MainDao<User> {
// MA działac na DTO

    List<User> user_list;

    private UserRepository userRepository;

    public BasicUserInformationService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public Map findAll() {
        return null;
    }


    public UserDto updateUserBasicInformation(final UserDto userDto) {
        return null;
    }
}
