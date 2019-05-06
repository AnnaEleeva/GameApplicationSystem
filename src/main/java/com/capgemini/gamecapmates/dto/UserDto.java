package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Game;
import lombok.*;

import java.util.List;

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

    private List<Long> userGames;
    private List<Long> userGamesHistory;
    private List<Long> userAvailabilityHours;
}
