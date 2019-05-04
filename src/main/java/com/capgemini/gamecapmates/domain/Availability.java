package com.capgemini.gamecapmates.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Availability {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Disponibility disponibility;
    private String information;
}
