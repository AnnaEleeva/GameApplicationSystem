package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.restService.BasicUserInformationWebService;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private BasicUserInformationWebService basicUserInformationWebService;
    private BasicUserInformationService basicUserInformationService;
    private UserRepository userRepository;

    @Autowired
    public UserRestController(BasicUserInformationWebService basicUserInformationWebService, BasicUserInformationService basicUserInformationService,
                              UserRepository userRepository){
        this.basicUserInformationWebService=basicUserInformationWebService;
        this.basicUserInformationService=basicUserInformationService;
        this.userRepository=userRepository;
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUser() throws Exception{
        List<UserDto> allUsers= basicUserInformationService.findAllUser();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserFromCollection(@PathVariable("id") Long id) throws Exception{
        UserDto user= basicUserInformationService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto userUpdateDto) throws Exception{
        UserDto updatedUser= basicUserInformationService.updateUserBasicInformation(userUpdateDto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<UserDto>> searchForUser(@RequestBody UserDto userDto){
        List<UserDto> findUser= basicUserInformationWebService.searchUser(userDto);
        return ResponseEntity.ok().body(findUser);
    }

}
