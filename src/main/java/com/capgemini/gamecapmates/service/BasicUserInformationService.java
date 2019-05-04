package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.StatisticsDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicUserInformationService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public BasicUserInformationService(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto updateUserBasicInformation(final UserUpdateDto userUpdate) throws NoSuchUserException {
        User user = userMapper.mapUserUpdateDtoToUser(userUpdate);

        User user1 = userRepository.save(user);

        return userMapper.mapEntityToDto(user1);
    }

    public UserDto findUserById(Long user_id) throws NoSuchUserException {
        User user_Id = userRepository.findById(user_id).orElse(null);

        return userMapper.mapEntityToDto(user_Id);
    }

    public StatisticsDto getUserStatistics(Long id){ // liczba wygranych, przegranych zremisowanych
       return null;
    }

    public StatisticsDto getPositionInRanking(Long id){
        return null;
    }

    public StatisticsDto getUserLevel(){
        return null;
        // calculate Level
    }

    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();

        return userMapper.mapListToDto(users);
    }
    // dont need to add user

    public void removeUser(UserDto userDto){}


}
