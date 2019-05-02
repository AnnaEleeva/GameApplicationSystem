package com.capgemini.gamecapmates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class HistoryDto {
    private LocalDate date;
    private String action;
    private String details;
    private Double rating;
}
