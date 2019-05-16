package com.gamecapmates.domain;

import com.gamecapmates.enums.Category;
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
    private Category category;
    private String description;

}
