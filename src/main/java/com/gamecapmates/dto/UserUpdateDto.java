package com.gamecapmates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String motto;
}
