package com.capgemini.gamecapmates.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Game{
    public Long id;
    private String name;
    private LocalDate year_of_publishment; //calendar
    private int minNumberOfPlayers;
    private int maxNumberOfplayers;
    private String description;

}
