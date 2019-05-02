package com.capgemini.gamecapmates.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class GameDto {
    private Long id;
    private String name;
    private LocalDate year_of_publishment; //calendar
    private int number_of_players1;
    private int number_of_players2;
    private String description;
}
