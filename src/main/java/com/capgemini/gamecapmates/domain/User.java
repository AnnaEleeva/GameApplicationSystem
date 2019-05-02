package com.capgemini.gamecapmates.domain;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class User{
    public Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
    private Level level;

}
