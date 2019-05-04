package com.capgemini.gamecapmates.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class GameDto {
    public Long id;
    private String name;
    private LocalDate year_of_publishment; //calendar
    private int minNumberOfPlayers;
    private int maxNumberOfplayers;
    private String description;
}
