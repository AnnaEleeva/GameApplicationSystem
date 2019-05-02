package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.UserRepository;


public class BasicUserInformationService {
    // dane beda rozszerone o id
    // usera mozna zobaczyc i zapdejtowac i usuwac gry !!!!!!!!

    private UserRepository userRepository;

    public BasicUserInformationService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserDto updateUserBasicInformation(final UserUpdateDto userUpdate) {
        UserMapper userMapper= new UserMapper();
        User user = userMapper.mapUserUpdateToDto(userUpdate);

       User user1= userRepository.save(user);

        return userMapper.mapEntityToDto(user1);
    }


    // nie przejmowac sie dodawaniem uzytkownika !!!!!!!!!
    //metoda pobieranie listy uzytkownikow
}
