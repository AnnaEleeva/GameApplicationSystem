package com.gamecapmates.domain;

import com.gamecapmates.enums.Disponibility;
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

    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Disponibility disponibility;
    private String information;
}
