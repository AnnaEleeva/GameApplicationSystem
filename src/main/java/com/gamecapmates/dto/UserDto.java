package com.gamecapmates.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;

    private List<Long> userGames;
    private List<Long> userGamesHistory;
    private List<Long> userAvailabilityHours;

    public UserDto(){}
}
