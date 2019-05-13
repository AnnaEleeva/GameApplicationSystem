package com.capgemini.gamecapmates.restService;

import com.capgemini.gamecapmates.domain.User;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BasicUserInformationWebService {

    private UserMapper userMapper;
    private UserRepository userRepository;

    @Autowired
    BasicUserInformationWebService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserDto> searchUser(UserDto userDto) {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUsersDto = allUsers.stream()
                .map(entity -> userMapper.mapEntityToDto(entity))
                .collect(Collectors.toList());

        Predicate<UserDto> name = userDto.getFirstName() == null || userDto.getFirstName().length() == 0 ?
                userDto1 -> userDto1.getFirstName().equalsIgnoreCase(userDto.getFirstName()) : userDto1 -> true;
        Predicate<UserDto> lastName = userDto.getLastName() == null || userDto.getLastName().length() == 0 ?
                userDto1 -> userDto1.getLastName().equalsIgnoreCase(userDto.getLastName()) : userDto1 -> true;
        Predicate<UserDto> games = userDto.getUserGames() == null || userDto.getUserGames().size() == 0 ?
                userDto1 -> hasUserGamesAsSearch(userDto1.getUserGames(), userDto.getUserGames()) : userDto1 -> true;

        List<UserDto> result = allUsersDto.stream()
                .filter(name)
                .filter(lastName)
                .filter(games)
                .collect(Collectors.toList());
        return result;
    }

    private boolean hasUserGamesAsSearch(List<Long> userGames, List<Long> gamesToSearch) {

        if (userGames.size() != gamesToSearch.size()) {
            return false;
        }
        for (Long i : userGames) {
            if (!gamesToSearch.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
