package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Disponibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AvailabilityDto {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Disponibility disponibility;
    private String information;
}
