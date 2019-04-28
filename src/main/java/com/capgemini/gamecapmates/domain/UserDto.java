package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
    private Level level;
}
