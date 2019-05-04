package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.User;
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
public class GamesHistoryDto {
    private Long id;
    private Optional<User> userId;
    private LocalDate date;
    private String action;
    private String details;
    private Double rating;
}
