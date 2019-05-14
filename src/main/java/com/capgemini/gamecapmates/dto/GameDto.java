package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.enums.Category;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class GameDto {
    public Long id;
    private String name;
    private LocalDate year_of_publishment; //calendar
    private int minNumberOfPlayers;
    private int maxNumberOfplayers;
    private Category category;
    private String description;

    public GameDto(){}
}
