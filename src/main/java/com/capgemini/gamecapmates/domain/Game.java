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
    private int number_of_players1;
    private int number_of_players2;// mapa , bo jest slash
    private String description;

}
