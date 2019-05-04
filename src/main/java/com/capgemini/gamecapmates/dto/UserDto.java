package com.capgemini.gamecapmates.dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
}
