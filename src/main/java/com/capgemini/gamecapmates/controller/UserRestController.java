package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.dto.UserUpdateDto;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.restService.BasicUserInformationWebService;
import com.capgemini.gamecapmates.service.BasicUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private BasicUserInformationService basicUserInformationService;


    @Autowired
    public UserRestController( BasicUserInformationService basicUserInformationService){
        this.basicUserInformationService=basicUserInformationService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserFromCollection(@PathVariable("id") Long id) throws NoSuchUserException {
        UserDto user= basicUserInformationService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/addUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        UserDto user = basicUserInformationService.addUser(userDto);
        return ResponseEntity.ok().body(user);
    }

}
