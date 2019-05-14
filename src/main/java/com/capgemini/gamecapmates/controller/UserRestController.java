package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.UserDto;
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

    /**
     *          Method which handles HTTP GET request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.UserDto} object found by its id
     * @param id
     *          Long id of object {@link com.capgemini.gamecapmates.dto.UserDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.UserDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.User} with given object is null
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserFromCollection(@PathVariable("id") Long id) throws Exception {
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        UserDto user= basicUserInformationService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    /**
     *          Method which handles HTTP POST request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.UserDto} object found by its id
     * @param userDto
     *          object {@link com.capgemini.gamecapmates.dto.UserDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.UserDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.User} with given object is null
     */
    @PostMapping(value = "/addUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        UserDto user = basicUserInformationService.addUser(userDto);
        return ResponseEntity.ok().body(user);
    }

}
