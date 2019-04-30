package com.capgemini.gamecapmates.domain;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
//Entity
public class User{
    private Long id;
    private int age;
    private LocalDate birthDate;
    private LocalDate currentDate;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
    private Level level;

}
