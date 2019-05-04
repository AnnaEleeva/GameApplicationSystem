package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GamesHistory {
    private Long id;
    private Optional<User> userId;
    private LocalDate date;
    private String action;
    private String details;
    private Double rating;
}
