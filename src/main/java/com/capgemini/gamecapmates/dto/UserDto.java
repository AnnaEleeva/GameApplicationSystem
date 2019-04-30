package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Level;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
    private Level level;
}
