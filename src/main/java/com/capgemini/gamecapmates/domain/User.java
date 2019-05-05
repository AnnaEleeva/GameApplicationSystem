package com.capgemini.gamecapmates.domain;

import lombok.*;

import java.util.List;


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

    private List<Game> userGames;
    private List<PreviousGames> userPreviousGames;
    private List<Availability> userTimeAvailability;
}
